package by.meshicage.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Role(BeanDefinition.ROLE_APPLICATION)
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic bookCreatedTopic() {
        return TopicBuilder.name("book-created")
                .partitions(3)
                .build();
    }

    @Bean
    public NewTopic bookDeletedTopic() {
        return TopicBuilder.name("book-deleted").build();
    }
}
