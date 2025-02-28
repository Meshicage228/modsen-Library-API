package by.meshicage.controller;

import by.meshicage.controller.doc.BookTrackingControllerDoc;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.service.TrackingService;
import by.meshicage.dto.CreatedBookTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@RestController
@RequestMapping("/api/v1/tracking")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@RequiredArgsConstructor
public class BookTrackingController implements BookTrackingControllerDoc {
    private final TrackingService service;

    public Page<CreatedBookTracking> getBookTrackingAvailable(Integer pageNum, Integer pageSize) {
        return service.getAllAvailableBooks(pageNum, pageSize);
    }

    public UpdatedBookTracking updateBookTrackingStatus(UpdateBookTrackingStatus bookTrackingStatus) {
        return service.updateTrackingBookStatus(bookTrackingStatus);
    }
}
