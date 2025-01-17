package by.meshicage.mapper;

import by.meshicage.dto.CreateBookTracking;
import by.meshicage.dto.CreatedBookTracking;
import by.meshicage.dto.UpdateBookTrackingStatus;
import by.meshicage.dto.UpdatedBookTracking;
import by.meshicage.entity.BookTrackingEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BookTrackingMapper {
    @Value("${app.returnRate:5}")
    private Integer returnRate;

    @Mapping(target = "borrowedAt", expression = "java(getCurrentDate())")
    @Mapping(target = "returnBy", expression = "java(getEndDate())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract BookTrackingEntity changeBookIsBorrowed(@MappingTarget BookTrackingEntity bookTrackingEntity,
                                                            UpdateBookTrackingStatus createBookTracking);

    @Named("getEndDate")
    public LocalDateTime getEndDate(){
        return LocalDateTime.now().plusDays(returnRate);
    }

    @Named("getCurrentDate")
    public LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }

    @Mapping(target = "borrowedAt", expression = "java(null)")
    @Mapping(target = "returnBy", expression = "java(null)")
    public abstract BookTrackingEntity changeBookIsFree(@MappingTarget BookTrackingEntity trackingEntity,
                                                        UpdateBookTrackingStatus createBookTracking);

    public abstract BookTrackingEntity toEntity(CreateBookTracking createBookTracking);

    public abstract CreatedBookTracking toCreatedBookTracking(BookTrackingEntity bookTrackingEntity);

    public abstract UpdatedBookTracking toUpdatedBookTracking(BookTrackingEntity bookTrackingEntity);
}
