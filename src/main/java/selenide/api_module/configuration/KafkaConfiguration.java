package selenide.api_module.configuration;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//Настраивает конфигурацию для Kafka-производителей и потребителей в Spring-приложении
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${kafka.incoming-external-messages.topic}")
    private String incomingMessagesKafkaTopic;

    @Value("${kafka.global-external-messages.topic}")
    private String globalMessagesKafkaTopic;

    @Bean
    @ConfigurationProperties("kafka.global-external-messages")
    public KafkaProperties globalMessagesKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    @ConfigurationProperties("kafka.incoming-external-messages")
    public KafkaProperties incomingMessagesKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    @ConfigurationProperties("kafka.outgoing-external-responses")
    public KafkaProperties outgoingResponsesKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public ProducerFactory<String, String> globalExternalMessagesKafkaProducerFactory(SslBundles sslBundles) {
        return new DefaultKafkaProducerFactory<>(globalMessagesKafkaProperties().buildProducerProperties(sslBundles));
    }

    @Bean
    public KafkaTemplate<String, String> globalExternalMessagesKafkaTemplate(@Qualifier("globalExternalMessagesKafkaProducerFactory") ProducerFactory<String, String> producerFactory) {
        final KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(globalMessagesKafkaTopic);
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, String> incomingExternalMessagesKafkaProducerFactory(SslBundles sslBundles) {
        return new DefaultKafkaProducerFactory<>(incomingMessagesKafkaProperties().buildProducerProperties(sslBundles));
    }

    @Bean
    public KafkaTemplate<String, String> incomingExternalMessagesKafkaTemplate(@Qualifier("incomingExternalMessagesKafkaProducerFactory") ProducerFactory<String, String> producerFactory) {
        final KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(incomingMessagesKafkaTopic);
        return kafkaTemplate;
    }

    @Bean
    public ConsumerFactory<String, String> outgoingExternalResponsesKafkaConsumerFactory(SslBundles sslBundles) {
        return new DefaultKafkaConsumerFactory<>(outgoingResponsesKafkaProperties().buildConsumerProperties(sslBundles));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> outgoingExternalResponsesKafkaContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }

    // Дополнительные конфигурации для Kafka продюсеров и консумеров
    @Bean
    public ProducerFactory<String, String> kafkaProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> kafkaConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return factory;
    }
}

