package by.meshicage.mapper;

import by.meshicage.dto.genre.CreateGenreDto;
import by.meshicage.dto.genre.GenreDto;
import by.meshicage.dto.genre.UpdateGenreDto;
import by.meshicage.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GenreMapper {
    GenreDto toDto(GenreEntity genreEntity);
    GenreEntity toEntity(CreateGenreDto createGenreDto);
}
