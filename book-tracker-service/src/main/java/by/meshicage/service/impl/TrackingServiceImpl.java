package by.meshicage.service.impl;

import by.meshicage.applicationexceptionstarter.exception.impl.tracking.TrackingNotFoundException;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.entity.BookTrackingEntity;
import by.meshicage.repository.TrackingRepository;
import by.meshicage.service.TrackingService;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.mapper.BookTrackingMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {
    private final TrackingRepository repository;
    private final BookTrackingMapper bookTrackingMapper;

    @Scheduled(cron = "${app.scheduling-check-expiration}")
    @Transactional
    public void finesFind(){
        repository.detectExpiredBooks();
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
}
