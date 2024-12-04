package selenide.api_module.model;

import lombok.Data;
import lombok.experimental.Accessors;

//класс хранит все необходимые параметры для подключения к Kafka, такие как пароли, местоположения файлов и настройки безопасности.
@Data
@Accessors(chain = true)
public class KafkaConfigDto {

    private String keyPassword;
    private String keyStoreLocation;
    private String keystorePassword;
    private String keystoreType;
    private String truststorePassword;
    private String truststoreType;
    private String trustStoreLocation;
    private String bootstrapServers;
    private String kafkaProtocol;
}
