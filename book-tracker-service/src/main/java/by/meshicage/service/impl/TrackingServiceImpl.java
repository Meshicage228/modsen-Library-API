package by.meshicage.service.impl;

import by.meshicage.applicationexceptionstarter.exception.impl.tracking.TrackingNotFoundException;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.entity.BookTrackingEntity;
import by.meshicage.repository.TrackingRepository;
import by.meshicage.service.TrackingService;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.mapper.BookTrackingMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {
    private final TrackingRepository repository;
    private final BookTrackingMapper bookTrackingMapper;

    @Override
    public CreatedBookTracking createTracking(CreateBookTracking tracking) {
        return Optional.of(bookTrackingMapper.toEntity(tracking))
                .map(repository::save)
                .map(bookTrackingMapper::toCreatedBookTracking)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<CreatedBookTracking> getAllAvailableBooks(Integer pageNum, Integer pageSize) {
        return repository.findByIsBorrowed(false, PageRequest.of(pageNum, pageSize))
                .map(bookTrackingMapper::toCreatedBookTracking);
    }

    @Override
    @Transactional
    public UpdatedBookTracking updateTrackingBookStatus(UpdateBookTrackingStatus tracking) {
        return repository.findByBookId(tracking.getBookId())
                .map(bookTrackingEntity -> {
                    BookTrackingEntity updatedEntity = tracking.getIsBorrowed()
                            ? bookTrackingMapper.changeBookIsBorrowed(bookTrackingEntity, tracking)
                            : bookTrackingMapper.changeBookIsFree(bookTrackingEntity, tracking);

                    return bookTrackingMapper.toUpdatedBookTracking(updatedEntity);
                })
                .orElseThrow(() -> new TrackingNotFoundException(tracking.getBookId()));
    }

    @Override
    @Transactional
    public void deleteTrackingByBookId(Long bookId) {
        repository.deleteByBookId(bookId);
    }
}
