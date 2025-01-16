package by.meshicage.dto.genre;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateGenreDto {
    @NotNull(message = "Provide genre id!")
    private Long id;
}
