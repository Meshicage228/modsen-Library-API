package by.meshicage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBookTrackingStatus {
    private Long bookId;
    private Boolean isBorrowed;
}
