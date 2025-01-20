package by.meshicage.client;

import by.meshicage.dto.tracking.CreateBookTracking;
import by.meshicage.dto.tracking.CreatedBookTracking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@FeignClient(
        name = "${app.clients.tracker.name}",
        url = "${app.clients.tracker.url}",
        path = "${app.clients.tracker.path}"
)
public interface TrackerClient {

    @PostMapping
    @ResponseStatus(CREATED)
    CreatedBookTracking createBookTracking(@RequestBody CreateBookTracking createBookTracking);

    @DeleteMapping("/{bookId}")
    @ResponseStatus(NO_CONTENT)
    void deleteBookTracking(@PathVariable Long bookId);
}