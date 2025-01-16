package by.meshicage.mapper;

import by.meshicage.dto.genre.CreateGenreDto;
import by.meshicage.dto.genre.GenreDto;
import by.meshicage.dto.genre.UpdateGenreDto;
import by.meshicage.entity.GenreEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {
    GenreDto toDto(GenreEntity genreEntity);
    GenreEntity toEntity(GenreDto genreDto);
    GenreEntity toEntity(CreateGenreDto createGenreDto);
    GenreEntity toEntity(UpdateGenreDto createGenreDto);
}
