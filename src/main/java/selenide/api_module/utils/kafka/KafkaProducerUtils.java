package selenide.api_module.utils.kafka;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

//Методы, отвечающие за отправку сообщений в kafka
public class KafkaProducerUtils {

    private KafkaTemplate<String, Object> kafkaTemplate;
    public String tranId = UUID.randomUUID().toString();

    //предназначен для логирования информации о успешно отправленном сообщении и добавления этой информации как вложения в отчёт
    private void logSuccessSend(SendResult<String, Object> result) {
        RecordMetadata metadata = result.getRecordMetadata();
        String message = String.format("""
                Сообщение успешно отправлено.
                Топик: %s,
                Партиция: %s,
                Offset: %s
                """, metadata.topic(), metadata.partition(), metadata.offset());
        log.info(message);
        Allure.addAttachment("Response", message);
    }

    //отправляет сообщение в указанный Kafka-топик асинхронно
    public void sendAsync(String topic, Object message) {
        try {
            log.info("Отправить сообщение '{}' в топик: '{}'", message, topic);
            kafkaTemplate.send(topic, message);
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
        }
    }

    //отправляет сообщение в Kafka синхронно, используя метод get()
    @SneakyThrows
    public void sendSync(String topic, Object message) {
        log.info("Отправить сообщение '{}' в топик: '{}'", message, topic);
        SendResult<String, Object> result = kafkaTemplate.send(topic, message).get();
        logSuccessSend(result);
    }

    //выполняет синхронную отправку, как и sendSync, но вместо простых аргументов (topic, message) принимает объект ProducerRecord
    /**
     ProducerRecord позволяет задавать дополнительные свойства сообщения:
        * Топик (topic).
        * Ключ (key), который используется для маршрутизации в определённую партицию.
        * Заголовки (headers) для передачи метаинформации.
     */
    @SneakyThrows
    public void sendSync(ProducerRecord<String, Object> producerRecord) {
        log.info("Отправить сообщение '{}' в топик: '{}'", producerRecord.value().toString(), producerRecord.topic());
        SendResult<String, Object> result = kafkaTemplate.send(producerRecord).get();
        logSuccessSend(result);
    }

    //отправляет сообщение с ключом в указанный Kafka-топик асинхронно.
    public void sendWithKeyAsync(String topic, String key, Object message) {
        try {
            log.info("Отправить сообщение '{}' в топик: '{}' с ключом '{}'", message, topic, key);
            kafkaTemplate.send(topic, key, message);
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
        }
    }

    //отправляет сообщение с ключом в Kafka-топик синхронно.
    @SneakyThrows
    public void sendWithKeySync(String topic, String key, Object message) {
        log.info("Отправить сообщение '{}' с ключом '{}' в топик: '{}'", message, key, topic);
        SendResult<String, Object> result = kafkaTemplate.send(topic, key, message).get();
        logSuccessSend(result);
    }

    //отправляет сообщение с хэдорами в Kafka-топик асинхронно.
    public String sendWithHeaders(String topic, Object message) {
        try {
            log.info("Отправить сообщение '{}' с ключом '{}' в топик: '{}'", message, tranId, topic);
            // Создаем ProducerRecord с указанным ключом (tranId) и сообщением
            ProducerRecord<String, Object> record = new ProducerRecord<>(topic, tranId, message);
            // Добавляем необходимые заголовки (пример ниже)
            record.headers().add("название-хэддора со временем", new Timestamp(new Date().getTime()).toString().getBytes());
            record.headers().add("название-хэдора с его жизненным временем", "2629800000".getBytes());
            record.headers().add("uuid-рандомный", UUID.randomUUID().toString().getBytes());
            // Отправляем сообщение в Kafka с помощью kafkaTemplate
            kafkaTemplate.send(record);
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
            // Возвращаем tranId для дальнейшего использования
            return tranId;
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
            return null;
        }
    }

    //отправляет сообщение с хэдорами в Kafka-топик асинхронно.
    @SneakyThrows
    public String sendWithHeadersSync(String topic, Object message) {
        log.info("Отправить сообщение '{}' в топик: '{}' с ключом '{}'", message, topic, tranId);
        // Создаем ProducerRecord с указанным ключом (tranId) и сообщением
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, tranId, message);
        // Добавляем необходимые заголовки
        record.headers().add("название-хэддора со временем", new Timestamp(new Date().getTime()).toString().getBytes());
        record.headers().add("название-хэддора с его жизненным временем", "2629800000".getBytes());
        record.headers().add("uuid-рандомный", UUID.randomUUID().toString().getBytes());
        try {
            // Отправляем сообщение в Kafka синхронно и получаем результат
            SendResult<String, Object> result = kafkaTemplate.send(record).get();  // .get() делает отправку синхронной
            logSuccessSend(result);  // Логируем результат отправки
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
            // Возвращаем tranId для дальнейшего использования
            return tranId;
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
            return null;
        }
    }
}
