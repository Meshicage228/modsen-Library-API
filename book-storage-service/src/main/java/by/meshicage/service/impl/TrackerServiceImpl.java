package by.meshicage.service.impl;

import by.meshicage.client.TrackerClient;
import by.meshicage.dto.tracking.CreateBookTracking;
import by.meshicage.dto.tracking.CreatedBookTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackerServiceImpl {
    private final TrackerClient client;

    public CreatedBookTracking createBookTracking(Long bookId){
        return client.createBookTracking(
                CreateBookTracking.builder()
                        .bookId(bookId)
                        .build());
    }

    public void deleteBookTracking(Long bookId){
        client.deleteBookTracking(bookId);
    }
}
