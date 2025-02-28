package by.meshicage.kafka;

import by.meshicage.applicationexceptionstarter.exception.impl.tracking.FailedToCreateTrackingException;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.mapper.BookTrackingMapper;
import by.meshicage.repository.TrackingRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final BookTrackingMapper bookTrackingMapper;
    private final TrackingRepository repository;

    @KafkaListener(topics = "book-created", groupId = "my_consumer")
    public void createTracking(ConsumerRecord<String, Long> record) {
        Optional.of(bookTrackingMapper.toEntity(new CreateBookTracking(record.value())))
                .map(repository::save)
                .map(bookTrackingMapper::toCreatedBookTracking)
                .orElseThrow(FailedToCreateTrackingException::new);
    }

    @Transactional
    @KafkaListener(topics = "book-deleted", groupId = "my_consumer")
    public void deleteTrackingByBookId(Long bookId) {
        repository.deleteByBookId(bookId);
    }
}
