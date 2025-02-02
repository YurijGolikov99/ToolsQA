package selenide.api_module.utils.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Assertions;
import org.springframework.kafka.core.ConsumerFactory;
import selenide.api_module.utils.WaitingUtils;
import selenide.common_module.utils.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

//Методы, отвечающие за получение сообщений в kafka
public class KafkaConsumerUtils {

    private ConsumerFactory <String, ObjectNode> consumerFactory;
    private WaitingUtils waitingUtils;
    private JsonUtils jsonUtils;

    //метод ищет одно сообщение в заданном топике (topic), значение которого содержит строку containsValue.
    public ConsumerRecord<String, ObjectNode> getRecordByMessage(String topic, String containsValue) {
        return getRecordsByMessage(topic, containsValue, 1).get(0);
    }

    //похож на предыдущий, но добавляет параметр timeout, который задаёт максимальное время ожидания (в секундах) для поиска сообщения.
    public ConsumerRecord<String, ObjectNode> getRecordByMessage(String topic, String containsValue, long timeout) {
        return getRecordsByMessage(topic, containsValue, 1, timeout).get(0);
    }

    //Метод ищет несколько сообщений (количество определяется параметром count) в заданном топике, значение которых содержит строку containsValue
    public List<ConsumerRecord<String, ObjectNode>> getRecordsByMessage(String topic, String containsValue, int count) {
        return getRecordsByMessage(topic, containsValue, count, 60L);
    }

    //Ищет в заданном топике сообщения, содержащие строку containsValue, и возвращает список из count сообщений.
    public List<ConsumerRecord<String, ObjectNode>> getRecordsByMessage(String topic, String containsValue, int count, long timeout) {
        List<ConsumerRecord<String, ObjectNode>> resultList = new ArrayList<>();
        try (Consumer<String, ObjectNode> consumer = consumerFactory.createConsumer()) {
            try {
                consumer.subscribe(Collections.singleton(topic));
                waitingUtils.waitUntil(timeout, 1L, String.format("Сообщение, содержащее '%s' не найдено в топике '%s'", containsValue, topic),
                        () -> {
                            ConsumerRecords<String, ObjectNode> records = consumer.poll(Duration.ofSeconds(1));
                            for (ConsumerRecord<String, ObjectNode> consumerRecord : records) {
                                if (consumerRecord.value().toString().contains(containsValue)) {
                                    log.info("Найдено сообщение содержащее'{}' в топике '{}'", containsValue, topic);
                                    resultList.add(consumerRecord);
                                    if (resultList.size() == count) {
                                        return true;
                                    }
                                }
                            }
                            return false;
                        });
                return resultList;
            } finally {
                consumer.unsubscribe();
            }
        }
    }

    //Ищет одно сообщение по ключу (key) в указанном топике (topic).
    public ConsumerRecord<String, ObjectNode> getRecordByKey(String topic, String key) {
        return getRecordsByKey(topic, key, 1).get(0);
    }

    //Ищет одно сообщение по ключу (key), но с возможностью задать тайм-аут ожидания (timeout).
    public ConsumerRecord<String, ObjectNode> getRecordByKey(String topic, String key, long timeout) {
        //count = 1 (ищется только одно сообщение).
        return getRecordsByKey(topic, key, 1, timeout).get(0);
    }

    //Ищет несколько сообщений (количество задаётся параметром count).
    public List<ConsumerRecord<String, ObjectNode>> getRecordsByKey(String topic, String key, int count) {
        //Использует тайм-аут по умолчанию (60 секунд).
        return getRecordsByKey(topic, key, count, 60L);
    }

    //Основной метод, содержащий всю логику поиска сообщений.
    /**
     *
     * @param topic топик Kafka, где выполняется поиск.
     * @param key строка, которая должна присутствовать в ключе сообщения.
     * @param count количество сообщений, которые нужно найти.
     * @param timeout максимальное время ожидания (в секундах).
     * @return
     */
    public List<ConsumerRecord<String, ObjectNode>> getRecordsByKey(String topic, String key, int count, long timeout) {
        List<ConsumerRecord<String, ObjectNode>> resultList = new ArrayList<>();
        try (Consumer<String, ObjectNode> consumer = consumerFactory.createConsumer()) {
            try {
                consumer.subscribe(Collections.singleton(topic));
                waitingUtils.waitUntil(timeout, 1L, String.format("Сообщение, содержащее ключ '%s' не найдено в топике '%s'", key, topic),
                        () -> {
                            ConsumerRecords<String, ObjectNode> records = consumer.poll(Duration.ofSeconds(1));
                            for (ConsumerRecord<String, ObjectNode> consumerRecord : records) {
                                if (consumerRecord.key().contains(key)) {
                                    log.info("Найдено сообщение содержащее ключ '{}' в топике '{}'", key, topic);
                                    resultList.add(consumerRecord);
                                    if (resultList.size() == count) {
                                        return true;
                                    }
                                }
                            }
                            return false;
                        });
                return resultList;
            } finally {
                consumer.unsubscribe();
            }
        }
    }

    //поиск сообщения в топике Kafka, у которого есть определённый заголовок (header) с указанным значением (value).
    public ConsumerRecord<String, ObjectNode> getRecordByHeader(String topic, String header, String value) {
        try (Consumer<String, ObjectNode> consumer = consumerFactory.createConsumer()) {
            try {
                consumer.subscribe(Collections.singleton(topic));
                AtomicReference<ConsumerRecord<String, ObjectNode>> result = new AtomicReference<>();
                waitingUtils.waitUntil(60, 1, String.format("Сообщение, содержащее хэдер '%s' со значением '%s' не найдено в топике '%s'", header, value, topic),
                        () -> {
                            ConsumerRecords<String, ObjectNode> records = consumer.poll(Duration.ofSeconds(1));
                            for (ConsumerRecord<String, ObjectNode> consumerRecord : records) {
                                if (consumerRecord.headers().lastHeader(header) != null && new String(consumerRecord.headers().lastHeader(header).value()).contains(value)) {
                                    log.info("Найдено сообщение, содержащее хэдер '{}' со значением '{}' в топике '{}'", header, value, topic);
                                    result.set(consumerRecord);
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

    //Извлекает значение конкретного заголовка из сообщения.
    public String getHeader(ConsumerRecord<String, ObjectNode> record, String header) {
        return new String (record.headers().lastHeader(header).value(), StandardCharsets.UTF_8);
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
        Consumer<String, ObjectNode> consumer = consumerFactory.createConsumer();
        try {
            consumer.subscribe(Collections.singleton(topic));
            AtomicReference<Boolean> isAbsent = new AtomicReference<>(true);
            waitingUtils.waitUntil(60L, 1L, String.format("Сообщение с '%s: %s' все еще присутствует в топике '%s'", fieldName, value, topic), () -> {
                ConsumerRecords<String, ObjectNode> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, ObjectNode> consumerRecord : records) {
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

}
