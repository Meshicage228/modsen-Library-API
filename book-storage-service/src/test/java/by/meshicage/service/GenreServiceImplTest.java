package by.meshicage.service;

import by.meshicage.applicationexceptionstarter.exception.impl.genre.GenreNotFoundException;
import by.meshicage.entity.GenreEntity;
import by.meshicage.repository.GenreRepository;
import by.meshicage.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@DisplayName("GenreService unit tests")
@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    private static final Long TEST_GENRE_ID = 1L;
    private static final String GENRE_NAME = "Fiction";
    private GenreEntity genreEntity;

    @BeforeEach
    public void setUp() {
        genreEntity = new GenreEntity();
        genreEntity.setId(TEST_GENRE_ID);
        genreEntity.setName(GENRE_NAME);
    }

    @Test
    @DisplayName("Find genre by ID successfully")
    public void testFindByIdSuccess() {
        // Given
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genreEntity));

        // When
        GenreEntity result = genreService.findById(TEST_GENRE_ID);

        // Then
        assertNotNull(result);
        assertEquals(TEST_GENRE_ID, result.getId());
        assertEquals(GENRE_NAME, result.getName());
    }

    @Test
    @DisplayName("Throw exception when genre not found by ID")
    public void testFindByIdThrowsException() {
        // Given
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When / Then
        assertThrows(GenreNotFoundException.class, () -> genreService.findById(TEST_GENRE_ID));
    }
}