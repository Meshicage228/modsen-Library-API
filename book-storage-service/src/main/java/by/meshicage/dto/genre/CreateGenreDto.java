package by.meshicage.dto.genre;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGenreDto {
    @NotNull(message = "Provide genre id!")
    private Long id;
}
