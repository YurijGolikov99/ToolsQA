package selenide.api_module.utils.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Assertions;
import org.springframework.kafka.core.ConsumerFactory;
import selenide.api_module.utils.WaitingUtils;
import selenide.common_module.utils.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

//Методы, отвечающие за получение сообщений в kafka
public class KafkaConsumerUtils {

    private ConsumerFactory <String, Object> consumerFactory;
    private WaitingUtils waitingUtils;
    private JsonUtils jsonUtils;

    //поиска сообщения в указанном топике Kafka, в содержимом (value) которого есть определенная подстрока containsValue.
    public ConsumerRecord<String, Object> getRecordByMessage(String topic, String containsValue) {
        try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
            try {
                consumer.subscribe(Collections.singleton(topic));
                AtomicReference<ConsumerRecord<String, Object>> result = new AtomicReference<>();
                waitingUtils.waitUntil(20, 1, String.format("Сообщение, содержащее '%s' не найдено в топике '%s'", containsValue, topic),
                        () -> {
                            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(1));
                            for (ConsumerRecord<String, Object> record : records) {
                                if (record.value().toString().contains(containsValue)) {
                                    log.info("Найдено сообщение '{}' в топике '{}'", record.value(), topic);
                                    result.set(record);
                                    return true;
                                }
                            }
                            return false;
                        });
                return result.get();
            } finally {
                consumer.unsubscribe();
            }
        }
    }

    //ищет сообщение в указанном топике Kafka, у которого есть заголовок header с заданным значением value.
    public ConsumerRecord<String, Object> getRecordByHeader(String topic, String header, String value) {
        try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
            try {
                consumer.subscribe(Collections.singleton(topic));
                AtomicReference<ConsumerRecord<String, Object>> result = new AtomicReference<>();
                waitingUtils.waitUntil(20, 1, String.format("Сообщение, содержащее хэдер '%s' со значением '%s' не найдено в топике '%s'", header, value, topic),
                        () -> {
                            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(1));
                            for (ConsumerRecord<String, Object> record : records) {
                                if (new String(record.headers().lastHeader(header).value()).contains(value)) {
                                    log.info("Найдено сообщение, содержащее хэдер '{}' со значением '{}' в топике '{}'", header, value, topic);
                                    result.set(record);
                                    return true;
                                }
                            }
                            return false;
                        });
                return result.get();
            } finally {
                consumer.unsubscribe();
            }
        }
    }

    //проверяют, отсутствует ли сообщение в указанном топике Kafka c данным ключом-значение.
    /**
     *Простая проверка: сообщение должно иметь точное совпадение значения.
     */
    public boolean isMessageNotPresent(String topic, String expectedValue) {
        try {
            getRecordByMessage(topic, expectedValue);
            Assertions.fail(String.format("Сообщение '%s' присутствует в топике '%s'", expectedValue, topic));
        } catch (AssertionError error){
            return false;
        }
        return true;
    }

    //проверяют, отсутствует ли сообщение в указанном топике Kafka, у которого есть заголовок поле с заданным значением value.
    /**
     * Более сложная проверка, анализирует содержимое JSON, поддерживает вложенные структуры.
     */
    public boolean isMessageNotPresent(String topic, String fieldName, String value) {
        Consumer<String, Object> consumer = consumerFactory.createConsumer();
        try {
            consumer.subscribe(Collections.singleton(topic));
            AtomicReference<Boolean> isAbsent = new AtomicReference<>(true);
            waitingUtils.waitUntil(60L, 1L, String.format("Сообщение с '%s: %s' все еще присутствует в топике '%s'", fieldName, value, topic), () -> {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, Object> consumerRecord : records) {
                    if (consumerRecord.value() != null && jsonUtils.containsFieldWithValue((JsonNode) consumerRecord.value(), fieldName, value)) {
                        log.info("Сообщение с '{}' = '{}' найдено в топике '{}'", fieldName, value, topic);
                        isAbsent.set(false);
                        return false;
                    }
                }
                return true;
            });
            return isAbsent.get();
        } finally {
            consumer.unsubscribe();
            consumer.close();
        }
    }

    //поиска сообщений по заголовку
    public String getHeader(ConsumerRecord<String, Object> record, String header) {
        return new String (record.headers().lastHeader(header).value(), StandardCharsets.UTF_8);
    }

}
