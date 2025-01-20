package by.meshicage.mapper;

import by.meshicage.dto.book.*;
import by.meshicage.entity.BookEntity;
import org.mapstruct.*;


@Mapper(
        uses = GenreMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookMapper {

    BookEntity toBookEntity(CreateBookDto book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "genre", ignore = true)
    BookEntity fullBookUpdate(@MappingTarget BookEntity targetBook, FullBookUpdateDto sourceBook);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity partBookUpdate(@MappingTarget BookEntity targetBook, PartUpdateBookDto sourceBook);

    CreatedBookDto toCreatedBookDto(BookEntity book);

    UpdatedBookDto toUpdatedBookDto(BookEntity book);
}
