package by.meshicage.kafka;

import by.meshicage.applicationexceptionstarter.exception.impl.tracking.FailedToCreateTrackingException;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.mapper.BookTrackingMapper;
import by.meshicage.repository.TrackingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final BookTrackingMapper bookTrackingMapper;
    private final TrackingRepository repository;

    @RetryableTopic(attempts = "5",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            exclude = {NullPointerException.class}, traversingCauses = "false",
            dltStrategy = DltStrategy.NO_DLT,
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000))
    @KafkaListener(topics = "book-created", groupId = "my_consumer",
            containerFactory = "bookTrackingKafkaListenerContainerFactory")
    public void createTracking(@Payload CreateBookTracking tracking,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Received new book tracking: id - {} with topic: {} from partition: {} ", tracking.getBookId(), topic, partition);
        Optional.of(bookTrackingMapper.toEntity(tracking))
                .map(repository::save)
                .map(bookTrackingMapper::toCreatedBookTracking)
                .orElseThrow(FailedToCreateTrackingException::new);
    }

    @Transactional
    @KafkaListener(topics = "book-deleted", groupId = "my_consumer",
            containerFactory = "longKafkaListenerContainerFactory")
    public void deleteTrackingByBookId(@Payload Long bookId,
                                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Received delete tracking by book-id: - {} with topic: {} from partition: {} ", bookId, topic, partition);
        repository.deleteByBookId(bookId);
    }
}
