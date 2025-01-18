package by.meshicage.service;

import by.meshicage.applicationexceptionstarter.exception.impl.tracking.TrackingNotFoundException;
import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.entity.BookTrackingEntity;
import by.meshicage.mapper.BookTrackingMapper;
import by.meshicage.repository.TrackingRepository;
import by.meshicage.service.impl.TrackingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("TrackingService unit tests")
@ExtendWith(MockitoExtension.class)
class TrackingServiceImplTest {
    @Mock
    private TrackingRepository repository;

    @Mock
    private BookTrackingMapper bookTrackingMapper;

    @InjectMocks
    private TrackingServiceImpl trackingService;

    private static final Long TEST_TRACKING_ID = 1L;
    private CreateBookTracking createBookTracking;
    private CreatedBookTracking createdBookTracking;
    private UpdateBookTrackingStatus updateBookTrackingStatus;
    private UpdatedBookTracking updatedBookTracking;
    private BookTrackingEntity bookTrackingEntity;

    @BeforeEach
    public void setUp() {
        createBookTracking = new CreateBookTracking();
        createdBookTracking = new CreatedBookTracking();
        updateBookTrackingStatus = UpdateBookTrackingStatus.builder().bookId(TEST_TRACKING_ID).isBorrowed(true).build();
        updatedBookTracking = new UpdatedBookTracking();
        bookTrackingEntity = new BookTrackingEntity();
    }

    @Test
    @DisplayName("Create tracking successfully")
    public void testCreateTracking() {
        // Given
        when(bookTrackingMapper.toEntity(any(CreateBookTracking.class))).thenReturn(bookTrackingEntity);
        when(repository.save(any(BookTrackingEntity.class))).thenReturn(bookTrackingEntity);
        when(bookTrackingMapper.toCreatedBookTracking(any(BookTrackingEntity.class))).thenReturn(createdBookTracking);

        // When
        CreatedBookTracking result = trackingService.createTracking(createBookTracking);

        // Then
        assertNotNull(result);
        assertEquals(createdBookTracking, result);
        verify(repository).save(any(BookTrackingEntity.class));
    }

    @Test
    @DisplayName("Get all available books")
    public void testGetAllAvailableBooks() {
        // Given
        Page<BookTrackingEntity> page = new PageImpl<>(List.of(bookTrackingEntity));
        when(repository.findByIsBorrowed(false, PageRequest.of(0, 10))).thenReturn(page);
        when(bookTrackingMapper.toCreatedBookTracking(any(BookTrackingEntity.class))).thenReturn(createdBookTracking);

        // When
        Page<CreatedBookTracking> result = trackingService.getAllAvailableBooks(0, 10);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(createdBookTracking, result.getContent().get(0));
    }

    @Test
    @DisplayName("Update tracking book status successfully")
    public void testUpdateTrackingBookStatus() {
        // Given
        when(repository.findByBookId(anyLong())).thenReturn(Optional.of(bookTrackingEntity));
        when(bookTrackingMapper.changeBookIsBorrowed(any(BookTrackingEntity.class), any(UpdateBookTrackingStatus.class)))
                .thenReturn(bookTrackingEntity);
        when(bookTrackingMapper.toUpdatedBookTracking(any(BookTrackingEntity.class))).thenReturn(updatedBookTracking);

        // When
        UpdatedBookTracking result = trackingService.updateTrackingBookStatus(updateBookTrackingStatus);

        // Then
        assertNotNull(result);
        assertEquals(updatedBookTracking, result);
        verify(repository).findByBookId(updateBookTrackingStatus.getBookId());
    }

    @Test
    @DisplayName("Throw exception when updating tracking for non-existent book")
    public void testUpdateTrackingBookStatusThrowsException() {
        // Given
        when(repository.findByBookId(anyLong())).thenReturn(Optional.empty());

        // When / Then
        assertThrows(TrackingNotFoundException.class, () -> trackingService.updateTrackingBookStatus(updateBookTrackingStatus));
    }

    @Test
    @DisplayName("Delete tracking by book ID successfully")
    public void testDeleteTrackingByBookId() {
        // Given
        doNothing().when(repository).deleteByBookId(anyLong());

        // When
        trackingService.deleteTrackingByBookId(TEST_TRACKING_ID);

        // Then
        verify(repository).deleteByBookId(TEST_TRACKING_ID);
    }
}