package by.meshicage.config;

import by.meshicage.dto.CreateBookTracking;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    private Map<String, Object> commonConsumerConfigs() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
        );
    }

    @Bean
    public Map<String, Object> consumerLongConfigs() {
        return new HashMap<>(commonConsumerConfigs()) {{
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        }};
    }

    @Bean
    public Map<String, Object> consumerObjConfigs() {
        return new HashMap<>(commonConsumerConfigs()) {{
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        }};
    }

    @Bean
    public ConsumerFactory<String, Long> consumerLongFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerLongConfigs(),
                new StringDeserializer(),
                new LongDeserializer()
        );
    }

    @Bean
    public ConsumerFactory<String, CreateBookTracking> consumerBookTrackingFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerObjConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(CreateBookTracking.class, false)
        );
    }

    private <T> KafkaListenerContainerFactory<?> createKafkaListenerContainerFactory(
            ConsumerFactory<String, T> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> longKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory(consumerLongFactory());
    }

    @Bean
    public KafkaListenerContainerFactory<?> bookTrackingKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory(consumerBookTrackingFactory());
    }
}
