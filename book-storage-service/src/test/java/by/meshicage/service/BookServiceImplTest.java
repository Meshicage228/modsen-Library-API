package by.meshicage.service;

import by.meshicage.applicationexceptionstarter.exception.impl.book.BookNotFoundException;
import by.meshicage.applicationexceptionstarter.exception.impl.book.BookUpdateException;
import by.meshicage.applicationexceptionstarter.exception.impl.genre.GenreNotFoundException;
import by.meshicage.dto.book.*;
import by.meshicage.dto.genre.CreateGenreDto;
import by.meshicage.entity.BookEntity;
import by.meshicage.entity.GenreEntity;
import by.meshicage.mapper.BookMapper;
import by.meshicage.repository.BookRepository;
import by.meshicage.service.impl.BookServiceImpl;
import by.meshicage.service.impl.TrackerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("BookServiceImpl unit tests")
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private GenreService genreService;

    @Mock
    private TrackerServiceImpl trackerService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private static final Long TEST_ID = 1L;
    private static final String TEST_ISBN = "1234567890";
    private static final String TEST_TITLE = "Title";
    private static final String UPDATED_TITLE = "Updated Title";
    private CreateBookDto createBookDto;
    private CreatedBookDto createdBookDto;
    private UpdatedBookDto updatedBookDto;
    private FullBookUpdateDto fullBookUpdateDto;
    private PartUpdateBookDto partUpdateBookDto;
    private BookEntity bookEntity;
    private GenreEntity genreEntity;

    @BeforeEach
    public void setUp() {
        CreateGenreDto genreDto = CreateGenreDto.builder().id(TEST_ID).build();
        createBookDto = CreateBookDto.builder().genre(genreDto).build();
        fullBookUpdateDto = FullBookUpdateDto.builder().title(TEST_TITLE).genre(genreDto).build();
        partUpdateBookDto = PartUpdateBookDto.builder().title(UPDATED_TITLE).build();
        genreEntity = new GenreEntity(TEST_ID, "Fiction");
        bookEntity = new BookEntity();
        updatedBookDto = UpdatedBookDto.builder().id(TEST_ID).title(UPDATED_TITLE).build();
        createdBookDto = CreatedBookDto.builder().id(TEST_ID).title(TEST_TITLE).build();
    }

    @Test
    @DisplayName("Create a book successfully")
    public void createBook() {
        // Given
        when(bookMapper.toBookEntity(any(CreateBookDto.class))).thenReturn(bookEntity);
        when(genreService.findById(anyLong())).thenReturn(genreEntity);
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toCreatedBookDto(any(BookEntity.class))).thenReturn(createdBookDto);

        // When
        CreatedBookDto result = bookService.createBook(createBookDto);

        // Then
        assertNotNull(result);
        assertEquals(TEST_TITLE, result.getTitle());
        verify(trackerService).createBookTracking(bookEntity.getId());
    }

    @Test
    @DisplayName("Full update of a book")
    public void updateBook() {
        // Given
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(bookEntity));
        when(genreService.findById(anyLong())).thenReturn(genreEntity);
        when(bookMapper.fullBookUpdate(any(BookEntity.class), any(FullBookUpdateDto.class))).thenReturn(bookEntity);
        when(bookMapper.toUpdatedBookDto(any(BookEntity.class))).thenReturn(updatedBookDto);

        // When
        UpdatedBookDto result = bookService.fullUpdate(TEST_ID, fullBookUpdateDto);

        // Then
        assertNotNull(result);
        assertEquals(UPDATED_TITLE, result.getTitle());
    }

    @Test
    @DisplayName("Partial update of a book")
    public void partialUpdateBook() {
        // Given
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(bookEntity));
        when(bookMapper.partBookUpdate(any(BookEntity.class), any(PartUpdateBookDto.class))).thenReturn(bookEntity);
        when(bookMapper.toUpdatedBookDto(any(BookEntity.class))).thenReturn(updatedBookDto);

        // When
        UpdatedBookDto result = bookService.partUpdate(TEST_ID, partUpdateBookDto);

        // Then
        assertNotNull(result);
        assertEquals(UPDATED_TITLE, result.getTitle());
    }

    @Test
    @DisplayName("Get a book by ID")
    public void getBookById() {
        // Given
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toCreatedBookDto(any(BookEntity.class))).thenReturn(createdBookDto);

        // When
        CreatedBookDto result = bookService.getBookById(TEST_ID);

        // Then
        assertNotNull(result);
        assertEquals(TEST_TITLE, result.getTitle());
    }

    @Test
    @DisplayName("Get a book by ISBN")
    public void getBookByISBN() {
        // Given
        when(bookRepository.findByIsbn(anyString())).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toCreatedBookDto(any(BookEntity.class))).thenReturn(createdBookDto);

        // When
        CreatedBookDto result = bookService.getBookByISBN(TEST_ISBN);

        // Then
        assertNotNull(result);
        assertEquals(TEST_TITLE, result.getTitle());
    }

    @Test
    @DisplayName("Get all books")
    public void getAllBooks() {
        // Given
        Page<BookEntity> bookPage = new PageImpl<>(List.of(bookEntity));
        when(bookRepository.findAll(PageRequest.of(0, 10))).thenReturn(bookPage);
        when(bookMapper.toCreatedBookDto(any(BookEntity.class))).thenReturn(createdBookDto);

        // When
        Page<CreatedBookDto> result = bookService.getAllBooks(0, 10);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(TEST_TITLE, result.getContent().get(0).getTitle());
    }

    @Test
    @DisplayName("Delete a book by ID")
    public void deleteBookById() {
        // When
        bookService.deleteBookById(TEST_ID);

        // Then
        verify(bookRepository).deleteById(TEST_ID);
        verify(trackerService).deleteBookTracking(TEST_ID);
    }

    @Nested
    @DisplayName("Service exception tests")
    public class ExceptionServiceTests {

        @Test
        @DisplayName("Exception genre not found during book creation")
        public void createBookThrowsGenreNotFound() {
            // Given
            when(bookMapper.toBookEntity(any(CreateBookDto.class))).thenReturn(bookEntity);
            when(genreService.findById(anyLong())).thenThrow(new GenreNotFoundException(TEST_ID));

            // When / Then
            assertThrows(GenreNotFoundException.class, () -> bookService.createBook(createBookDto));
        }

        @Test
        @DisplayName("Exception book not found during full update")
        public void fullUpdateThrowsBookNotFound() {
            // Given
            when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

            // When / Then
            assertThrows(BookUpdateException.class, () -> bookService.fullUpdate(TEST_ID, fullBookUpdateDto));
        }

        @Test
        @DisplayName("Exception book not found during partial update")
        public void partUpdateThrowsBookNotFound() {
            // Given
            when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

            // When / Then
            assertThrows(BookUpdateException.class, () -> bookService.partUpdate(TEST_ID, partUpdateBookDto));
        }

        @Test
        @DisplayName("Exception book not found by ID")
        public void getBookByIdThrowsNotFound() {
            // Given
            when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

            // When / Then
            assertThrows(BookNotFoundException.class, () -> bookService.getBookById(TEST_ID));
        }
    }
}
