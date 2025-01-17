package by.meshicage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatedBookTracking {
    private Long id;
    private Long bookId;
    private Boolean isBorrowed;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnBy;
    private Boolean isDeleted;
}
