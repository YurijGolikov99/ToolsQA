package selenide.api_module.utils.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

//Методы, отвечающие за отправку сообщений в kafka
public class KafkaProducerUtils {

    private KafkaTemplate<String, Object> kafkaTemplate;
    public String tranId = UUID.randomUUID().toString();

    //Отправка в топик сообщения
    public void send(String topic, Object message) {
        try {
            log.info("Отправить сообщение '{}' в топик: '{}'", message, topic);
            kafkaTemplate.send(topic, message);
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
        }
    }

    //Отправка в топик ключ + сообщение
    public void sendWithKey(String topic, String key, Object message) {
        try {
            log.info("Отправить сообщение '{}' в топик: '{}' с ключом '{}'", message, topic, key);
            kafkaTemplate.send(topic, message);
            log.info("Сообщение успешно отправлено в топик '{}'", topic);
        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения в топик '{}'", topic, e);
        }
    }

    //Отправка сообщения с хэдорами в топик
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
}
