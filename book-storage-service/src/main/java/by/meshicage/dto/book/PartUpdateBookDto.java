package by.meshicage.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartUpdateBookDto {
    private String isbn;
    private String title;
    private String description;
    private String author;
}
