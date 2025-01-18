package by.meshicage.dto.book;

import by.meshicage.annotation.UniqueISBN;
import by.meshicage.dto.genre.CreateGenreDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullBookUpdateDto {
    @UniqueISBN
    @NotBlank(message = "ISBN cannot be blank")
    @Size(max = 20, message = "ISBN must be at most 20 characters long")
    private String isbn;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be at most 255 characters long")
    private String title;

    @NotNull(message = "Genre cannot be null")
    private CreateGenreDto genre;

    @Size(max = 500, message = "Description must be at most 500 characters long")
    @NotBlank(message = "Provide description")
    private String description;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author must be at most 100 characters long")
    private String author;
}
