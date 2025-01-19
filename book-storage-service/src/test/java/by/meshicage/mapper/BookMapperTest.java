package by.meshicage.mapper;

import by.meshicage.dto.book.*;
import by.meshicage.dto.genre.CreateGenreDto;
import by.meshicage.entity.BookEntity;
import by.meshicage.entity.GenreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookMapper unit tests")
public class BookMapperTest {
    private static final Long BOOK_ID = 1L;
    private static final String ISBN = "1234567890123";
    private static final Long GENRE_ID = 1L;
    private BookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        bookMapper = Mappers.getMapper(BookMapper.class);
    }


    @Test
    @DisplayName("Full update of BookEntity from FullBookUpdateDto")
    public void testFullBookUpdate() {
        // Given
        BookEntity targetBook = new BookEntity();
        targetBook.setId(BOOK_ID);
        targetBook.setIsbn(ISBN);
        targetBook.setTitle("Old Title");
        targetBook.setDescription("Old Description");
        targetBook.setAuthor("Old Author");
        targetBook.setGenre(new GenreEntity());

        FullBookUpdateDto fullUpdateDto = FullBookUpdateDto.builder()
                .isbn("9876543210987")
                .title("New Title")
                .genre(CreateGenreDto.builder().id(GENRE_ID).build())
                .description("New Description")
                .author("New Author")
                .build();

        // When
        bookMapper.fullBookUpdate(targetBook, fullUpdateDto);

        // Then
        assertEquals(fullUpdateDto.getIsbn(), targetBook.getIsbn());
        assertEquals(fullUpdateDto.getTitle(), targetBook.getTitle());
        assertNotNull(targetBook.getGenre());
        assertEquals(fullUpdateDto.getDescription(), targetBook.getDescription());
        assertEquals(fullUpdateDto.getAuthor(), targetBook.getAuthor());
    }

    @Test
    @DisplayName("Partial update of BookEntity from PartUpdateBookDto")
    public void testPartBookUpdate() {
        // Given
        BookEntity targetBook = new BookEntity();
        targetBook.setId(BOOK_ID);
        targetBook.setIsbn(ISBN);
        targetBook.setTitle("Old Title");
        targetBook.setDescription("Old Description");
        targetBook.setAuthor("Old Author");

        PartUpdateBookDto partUpdateDto = PartUpdateBookDto.builder()
                .isbn("9876543210987")
                .title("New Title")
                .description("New Description")
                .build();

        // When
        bookMapper.partBookUpdate(targetBook, partUpdateDto);

        // Then
        assertEquals(partUpdateDto.getIsbn(), targetBook.getIsbn());
        assertEquals(partUpdateDto.getTitle(), targetBook.getTitle());
        assertEquals(partUpdateDto.getDescription(), targetBook.getDescription());
        assertEquals("Old Author", targetBook.getAuthor());
    }
}