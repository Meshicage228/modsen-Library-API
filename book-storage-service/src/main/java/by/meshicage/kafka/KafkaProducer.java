package by.meshicage.kafka;

import by.meshicage.dto.tracking.CreateBookTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Long> kafkaLongTemplate;
    private final KafkaTemplate<String, CreateBookTracking> kafkaObjTemplate;

    public void createBookTracking(Long bookId) {
        kafkaObjTemplate.send("book-created",
                CreateBookTracking.builder()
                .bookId(bookId)
                .build());
    }

    public void deleteBookTracking(Long bookId) {
        kafkaLongTemplate.send("book-deleted", bookId);
    }
}
