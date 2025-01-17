package by.meshicage.controller;

import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.service.TrackingService;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/tracking")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@RequiredArgsConstructor
public class BookTrackingController {
    private final TrackingService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreatedBookTracking createBookTracking(@RequestBody CreateBookTracking createBookTracking) {
        return service.createTracking(createBookTracking);
    }

    @GetMapping("/available")
    public Page<CreatedBookTracking> getBookTrackingAvailable(@RequestParam(required = false, defaultValue = "0") Integer pageNum ,
                                                              @RequestParam(required = false, defaultValue = "100") Integer pageSize) {
        return service.getAllAvailableBooks(pageNum, pageSize);
    }

    @PatchMapping
    public UpdatedBookTracking updateBookTrackingStatus(@RequestBody UpdateBookTrackingStatus bookTrackingStatus) {
        return service.updateTrackingBookStatus(bookTrackingStatus);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBookTracking(@PathVariable Long bookId) {
        service.deleteTrackingByBookId(bookId);
    }
}
