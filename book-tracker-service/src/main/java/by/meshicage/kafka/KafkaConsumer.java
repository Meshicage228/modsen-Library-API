package by.meshicage.kafka;

import by.meshicage.dto.CreateBookTracking;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "book-created", groupId = "my_consumer")
    public void listen(ConsumerRecord<String, Long> record) {
        System.out.println(record.value());
    }
}
