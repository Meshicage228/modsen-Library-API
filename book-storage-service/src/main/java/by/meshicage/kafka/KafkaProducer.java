package by.meshicage.kafka;

import by.meshicage.dto.tracking.CreateBookTracking;
import by.meshicage.dto.tracking.CreatedBookTracking;
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

//    public void deleteBookTracking(String topic, Long bookId){
//        kafkaTemplate.send(topic, bookId);
//    }
}
