package by.meshicage.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void createBookTracking(Long bookId){
        kafkaTemplate.send("book-created", bookId);
    }

    public void deleteBookTracking(Long bookId){
        kafkaTemplate.send("book-deleted", bookId);
    }
}
