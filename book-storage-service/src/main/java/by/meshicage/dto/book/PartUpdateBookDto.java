package by.meshicage.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartUpdateBookDto {
    private String isbn;
    private String title;
    private String description;
    private String author;
}
