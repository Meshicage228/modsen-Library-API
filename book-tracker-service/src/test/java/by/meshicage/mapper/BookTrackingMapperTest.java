package by.meshicage.mapper;

import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.entity.BookTrackingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookTrackingMapper unit tests")
public class BookTrackingMapperTest {
    private static final Long BOOK_ID = 1L;
    private static final Long TRACKING_ID = 1L;
    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final Integer RETURN_RATE = 5;
    private BookTrackingMapper bookTrackingMapper;

    @BeforeEach
    public void setUp() {
        bookTrackingMapper = Mappers.getMapper(BookTrackingMapper.class);
    }

    @Test
    @DisplayName("Change book status to free")
    public void testChangeToFree() {
        // Given
        UpdateBookTrackingStatus updateStatus = new UpdateBookTrackingStatus(BOOK_ID, false);
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setBookId(BOOK_ID);
        bookTrackingEntity.setIsBorrowed(true);
        bookTrackingEntity.setBorrowedAt(NOW);
        bookTrackingEntity.setReturnBy(NOW.plusDays(RETURN_RATE));

        // When
        bookTrackingMapper.changeBookIsFree(bookTrackingEntity, updateStatus);

        // Then
        assertFalse(bookTrackingEntity.getIsBorrowed());
        assertNull(bookTrackingEntity.getBorrowedAt());
        assertNull(bookTrackingEntity.getReturnBy());
    }

    @Test
    @DisplayName("Convert CreateBookTracking to BookTrackingEntity")
    public void testToEntity() {
        // Given
        CreateBookTracking createBookTracking = new CreateBookTracking(BOOK_ID);

        // When
        BookTrackingEntity bookTrackingEntity = bookTrackingMapper.toEntity(createBookTracking);

        // Then
        assertNotNull(bookTrackingEntity);
        assertEquals(BOOK_ID, bookTrackingEntity.getBookId());
        assertFalse(bookTrackingEntity.getIsBorrowed());
        assertNull(bookTrackingEntity.getBorrowedAt());
        assertNull(bookTrackingEntity.getReturnBy());
    }

    @Test
    @DisplayName("Convert BookTrackingEntity to CreatedBookTracking")
    public void testToCreatedBookTracking() {
        // Given
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setId(TRACKING_ID);
        bookTrackingEntity.setBookId(BOOK_ID);
        bookTrackingEntity.setIsBorrowed(true);
        bookTrackingEntity.setBorrowedAt(NOW);
        bookTrackingEntity.setReturnBy(NOW.plusDays(RETURN_RATE));
        bookTrackingEntity.setIsDeleted(false);

        // When
        CreatedBookTracking createdBookTracking = bookTrackingMapper.toCreatedBookTracking(bookTrackingEntity);

        // Then
        assertNotNull(createdBookTracking);
        assertEquals(TRACKING_ID, createdBookTracking.getId());
        assertEquals(BOOK_ID, createdBookTracking.getBookId());
        assertTrue(createdBookTracking.getIsBorrowed());
        assertNotNull(createdBookTracking.getBorrowedAt());
        assertNotNull(createdBookTracking.getReturnBy());
        assertFalse(createdBookTracking.getIsDeleted());
    }

    @Test
    @DisplayName("Convert BookTrackingEntity to UpdatedBookTracking")
    public void testToUpdatedBookTracking() {
        // Given
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setId(TRACKING_ID);
        bookTrackingEntity.setBookId(BOOK_ID);
        bookTrackingEntity.setIsBorrowed(false);
        bookTrackingEntity.setBorrowedAt(null);
        bookTrackingEntity.setReturnBy(null);
        bookTrackingEntity.setIsDeleted(false);

        // When
        UpdatedBookTracking updatedBookTracking = bookTrackingMapper.toUpdatedBookTracking(bookTrackingEntity);

        // Then
        assertNotNull(updatedBookTracking);
        assertNotNull(updatedBookTracking);
        assertEquals(TRACKING_ID, updatedBookTracking.getId());
        assertEquals(BOOK_ID, updatedBookTracking.getBookId());
        assertFalse(updatedBookTracking.getIsBorrowed());
        assertNull(updatedBookTracking.getBorrowedAt());
        assertNull(updatedBookTracking.getReturnBy());
        assertFalse(updatedBookTracking.getIsDeleted());
    }
}