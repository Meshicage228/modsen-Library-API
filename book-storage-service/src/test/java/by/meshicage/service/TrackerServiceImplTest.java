package by.meshicage.service;

import by.meshicage.client.TrackerClient;
import by.meshicage.dto.tracking.CreateBookTracking;
import by.meshicage.dto.tracking.CreatedBookTracking;
import by.meshicage.service.impl.TrackerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("TrackerService unit tests")
@ExtendWith(MockitoExtension.class)
class TrackerServiceImplTest {
    @Mock
    private TrackerClient client;

    @InjectMocks
    private TrackerServiceImpl trackerService;

    private static final Long TEST_BOOK_ID = 1L;
    private CreatedBookTracking createdBookTracking;
    private CreateBookTracking createBookTracking;

    @BeforeEach
    public void setUp() {
        createBookTracking = CreateBookTracking.builder()
                .bookId(TEST_BOOK_ID)
                .build();
        createdBookTracking = new CreatedBookTracking();
        createdBookTracking.setBookId(TEST_BOOK_ID);
    }

    @Test
    @DisplayName("Create book tracking successfully")
    public void testCreateBookTracking() {
        // Given
        when(client.createBookTracking(any(CreateBookTracking.class))).thenReturn(createdBookTracking);

        // When
        CreatedBookTracking result = trackerService.createBookTracking(TEST_BOOK_ID);

        // Then
        assertNotNull(result);
        assertEquals(TEST_BOOK_ID, result.getBookId());
        verify(client).createBookTracking(createBookTracking);
    }

    @Test
    @DisplayName("Delete book tracking successfully")
    public void testDeleteBookTracking() {
        // When
        trackerService.deleteBookTracking(TEST_BOOK_ID);

        // Then
        verify(client).deleteBookTracking(TEST_BOOK_ID);
    }
}