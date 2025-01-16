package by.meshicage.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateGenreDto {
    @NotNull(message = "Provide message id!")
    private Long id;

    @NotBlank(message = "Name of genre cannot be blank")
    @Size(max = 100, message = "Name of genre must be at most 100 characters long")
    private String name;
}
