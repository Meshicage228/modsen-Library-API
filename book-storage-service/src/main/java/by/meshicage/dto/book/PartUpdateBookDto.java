package by.meshicage.dto.book;

import lombok.Data;

@Data
public class PartUpdateBookDto {
    private String isbn;
    private String title;
    private String description;
    private String author;
}
