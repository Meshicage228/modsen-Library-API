package by.meshicage.config;

import by.meshicage.dto.tracking.CreateBookTracking;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public Map<String, Object> producerLongConfigs() {
        return new HashMap<>() {{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        }};
    }

    @Bean
    public Map<String, Object> producerObjConfigs() {
        return new HashMap<>() {{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        }};
    }

    @Bean
    public ProducerFactory<String, Long> producerLongFactory() {
        return new DefaultKafkaProducerFactory<>(producerLongConfigs());
    }

    @Bean
    public KafkaTemplate<String, Long> kafkaLongTemplate() {
        return new KafkaTemplate<>(producerLongFactory());
    }

    @Bean
    public ProducerFactory<String, CreateBookTracking> producerObjFactory() {
        return new DefaultKafkaProducerFactory<>(producerObjConfigs());
    }

    @Bean
    public KafkaTemplate<String, CreateBookTracking> kafkaObjTemplate() {
        return new KafkaTemplate<>(producerObjFactory());
    }
}
