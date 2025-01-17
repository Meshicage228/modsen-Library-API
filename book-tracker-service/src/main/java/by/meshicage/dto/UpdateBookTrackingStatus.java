package by.meshicage.dto;

import lombok.Data;

@Data
public class UpdateBookTrackingStatus {
    private Long bookId;
    private Boolean isBorrowed;
}
