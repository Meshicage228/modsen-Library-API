package by.meshicage.dto.book;

import by.meshicage.dto.genre.GenreDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class UpdatedBookDto {
    private Long id;
    private String isbn;
    private String title;
    private GenreDto genre;
    private String description;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
