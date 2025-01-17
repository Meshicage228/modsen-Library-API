package by.meshicage.service;

import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import org.springframework.data.domain.Page;

public interface TrackingService {
    CreatedBookTracking createTracking(CreateBookTracking tracking);
    Page<CreatedBookTracking> getAllAvailableBooks(Integer pageNum, Integer pageSize);
    UpdatedBookTracking updateTrackingBookStatus(UpdateBookTrackingStatus tracking);

    void deleteTrackingByBookId(Long bookId);
}
