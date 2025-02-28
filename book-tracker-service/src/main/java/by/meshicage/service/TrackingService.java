package by.meshicage.service;

import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import org.springframework.data.domain.Page;

public interface TrackingService {
    Page<CreatedBookTracking> getAllAvailableBooks(Integer pageNum, Integer pageSize);
    UpdatedBookTracking updateTrackingBookStatus(UpdateBookTrackingStatus tracking);
}
