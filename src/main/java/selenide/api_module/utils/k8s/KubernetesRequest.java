package selenide.api_module.utils.k8s;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentSpec;
import io.fabric8.kubernetes.api.model.networking.v1.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1.IngressTLS;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import selenide.api_module.model.DbConfigDto;
import selenide.api_module.model.KafkaConfigDto;
import selenide.api_module.utils.WaitingUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// утилиты для взаимодействия с Kubernetes в рамках автоматизации тестирования.
@Slf4j
public class KubernetesRequest {

    public static final String NAMESPACE = System.getenv("NAMESPACE");

    @Autowired
    private WaitingUtils waitingUtils;
    private final Config config = new ConfigBuilder().build();

    //Изменяет количество реплик в указанном Deployment
    public void setNumberOfReplicas(int numberOfReplicas, String deploymentName) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            Deployment deployment = client.apps().deployments().inNamespace(NAMESPACE).withName(deploymentName).get();
            DeploymentSpec spec = deployment.getSpec();
            int actualReplicas = spec.getReplicas();

            if (actualReplicas != numberOfReplicas) {
                log.info("Изменить количество реплик деплоймента '{}' на {}", deploymentName, numberOfReplicas);
                spec.setReplicas(numberOfReplicas);
                client.apps().deployments().inNamespace(NAMESPACE).withName(deploymentName).patch(deployment);
                if (numberOfReplicas == 0) {
                    waitTerminating(client, deploymentName);
                } else waitRunning(client, deploymentName);
            }
        }
    }

    //Ожидает, пока все поды указанного Deployment завершат работу
    private void waitTerminating(KubernetesClient client, String deploymentName) {
        log.info("Дождаться, что все контейнеры под деплоймента '{}' завершат работу", deploymentName);
        waitingUtils.waitUntil(300, 10, String.format("Не все поды деплоймента '%s' завершили работу", deploymentName),
                () -> client.pods().inNamespace(NAMESPACE).withLabel("app", deploymentName).list().getItems().isEmpty());
    }

    //Ожидает, пока все поды указанного Deployment перейдут в статус Running.
    private void waitRunning(KubernetesClient client, String deploymentName) {
        log.info("Дождаться, что все контейнеры под деплоймента '{}' перейдут в статус 'Running'", deploymentName);
        waitingUtils.waitUntil(300, 10, String.format("Не все контейнеры под деплоймента '%s' перешли в статус 'Running'", deploymentName),
                () -> {
                    PodList podList = client.pods().inNamespace(NAMESPACE).withLabel("app", deploymentName).list();
                    return podList.getItems().stream()
                            .allMatch(pod -> pod.getStatus().getContainerStatuses().stream()
                                    .allMatch(ContainerStatus::getReady));
                });
    }

    //Извлекает логи из контейнера пода, связанного с указанным Deployment, и проверяет наличие заданного текста.
    public String getPodLogs(int timeout, int pollInterval, String deploymentName,
                             String containerName, String containsText) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            AtomicReference<String> result = new AtomicReference<>();
            waitingUtils.waitUntil(timeout, pollInterval,
                    String.format("Логи, содержащие сообщение '%s' не найдены в контейнере '%s' деплоймента '%s'", containsText, containerName, deploymentName),
                    () -> {
                        PodList podList = client.pods().inNamespace(NAMESPACE).withLabel("app", deploymentName).list();
                        podList.getItems().forEach(pod -> {
                            String podName = pod.getMetadata().getName();
                            log.info("Извлечь логи из контейнера '{}' пода '{}'", containerName, podName);
                            String logs = client.pods()
                                    .inNamespace(NAMESPACE)
                                    .withName(podName)
                                    .inContainer(containerName)
                                    .getLog();
                            if (logs.contains(containsText)) {
                                result.set(logs);
                            }
                        });
                        return result.get() != null;
                    });
            log.info("Текст '{}' найден в логах контейнера", containsText);
            return result.get();
        }
    }

    //Извлекает значение из Secret по ключу, декодирует его и сохраняет в файл.
    @SneakyThrows
    public File getSecretFile(String secretName, String key, String outputPath) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            Secret secret = client.secrets().inNamespace(NAMESPACE).withName(secretName).get();
            String encodedValue = secret.getData().get(key);
            byte[] decodedValue = Base64.getDecoder().decode(encodedValue);
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(decodedValue);
            }
            return new File(outputPath);
        }
    }

    //Извлекает значение из Secret по ключу, декодирует его и возвращает как строку.
    @SneakyThrows
    public String getSecretString(String secretName, String key) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            Secret secret = client.secrets().inNamespace(NAMESPACE).withName(secretName).get();
            String encodedValue = secret.getData().get(key);
            return new String(Base64.getDecoder().decode(encodedValue));
        }
    }

    //Извлекает значение параметра из ConfigMap по его имени.
    @SneakyThrows
    public String getConfigMapParam(String configMapName, String param) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            ConfigMap configMap = client.configMaps().inNamespace(NAMESPACE).withName(configMapName).get();
            return configMap.getData().get(param);
        }
    }

    //Создает объект DbConfigDto, заполняя его данными из ConfigMap и Secret.
    public DbConfigDto getDbConfigDto(String configMap, String secret) {
        DbConfigDto result = new DbConfigDto()
                .setUser(getConfigMapParam(configMap, "DB_USER"))
                .setPassword(getSecretString(secret, "DB_PASSWORD"))
                .setName(getConfigMapParam(configMap, "DB_NAME"));
        result.setUrl(String.format("jdbc:postgresql://%s/%s", getConfigMapParam(configMap, "DB_URL"), result.getName()));
        return result;
    }

    //Создает объект KafkaConfigDto, заполняя его данными из Secret и ConfigMap.
    public KafkaConfigDto getKafkaConfigDto(String secret, String tlsSecret, String configMap) {
        String keyStoreOutPath = "keystore.pfx";
        String trustStoreOutPath = "truststore.pfx";

        if (NAMESPACE.contains("ppcd")) {
            return new KafkaConfigDto()
                    .setKeyPassword(getSecretString(secret, "KEY_PWD"))
                    .setKeystorePassword(getSecretString(secret, "KEYSTORE_PWD"))
                    .setKeyStoreLocation(getSecretFile(tlsSecret, "keystore.pfx", keyStoreOutPath).getAbsolutePath())
                    .setKeystoreType(getConfigMapParam(configMap, "KAFKA_KEYSTORE_TYPE"))
                    .setTruststoreType(getConfigMapParam(configMap, "KAFKA_TRUSTSTORE_TYPE"))
                    .setTruststorePassword(getSecretString(secret, "TRUSRSTORE_PWD"))
                    .setTrustStoreLocation(getSecretFile(tlsSecret, "truststore.pfx", trustStoreOutPath).getAbsolutePath())
                    .setBootstrapServers(getConfigMapParam(configMap, "KAFKA_BOOTSTRAP_SERVERS"))
                    .setKafkaProtocol(getConfigMapParam(configMap, "KAFKA_SECURITY_PROTOCOL"));
        } else {
            return new KafkaConfigDto()
                    .setKeyPassword(getSecretString(secret, "KAFKA_KEY_PASSWORD"))
                    .setKeystorePassword(getSecretString(secret, "KAFKA_KEYSTORE_PASSWORD"))
                    .setKeyStoreLocation(getSecretFile(tlsSecret, "keystore.pfx", keyStoreOutPath).getAbsolutePath())
                    .setKeystoreType(getConfigMapParam(configMap, "KAFKA_KEYSTORE_TYPE"))
                    .setTruststoreType(getConfigMapParam(configMap, "KAFKA_TRUSTSTORE_TYPE"))
                    .setTruststorePassword(getSecretString(secret, "KAFKA_TRUSTSTORE_PASSWORD"))
                    .setTrustStoreLocation(getSecretFile(tlsSecret, "truststore.pfx", trustStoreOutPath).getAbsolutePath())
                    .setBootstrapServers(getConfigMapParam(configMap, "KAFKA_SERVER"))
                    .setKafkaProtocol(getConfigMapParam(configMap, "KAFKA_SECURITY_PROTOCOL"));
        }

    }

    //Извлекает хост и протокол (http или https) из объекта Ingress
    public String getIngressHost(String ingressName) {
        try (KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build()) {
            Ingress ingress = client.network().v1().ingresses().inNamespace(NAMESPACE).withName(ingressName).get();
            if (ingress.getSpec().getRules().isEmpty()) {
                throw new NullPointerException("Ingress с именем '" + ingressName + "' не найден");
            }
            String host = ingress.getSpec().getRules().get(0).getHost();
            String protocol = "https";
            List<IngressTLS> ingressTLS = ingress.getSpec().getTls();
            if (ingressTLS.isEmpty()) protocol = "http";
            return String.format("%s://%s", protocol, host);
        }
    }

}
