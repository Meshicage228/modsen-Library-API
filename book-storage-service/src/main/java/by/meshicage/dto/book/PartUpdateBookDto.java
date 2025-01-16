package by.meshicage.dto.book;

import by.meshicage.dto.genre.GenreDto;
import lombok.Data;

@Data
public class PartUpdateBookDto {
    private String isbn;
    private String title;
    private GenreDto genre;
    private String description;
    private String author;
}
