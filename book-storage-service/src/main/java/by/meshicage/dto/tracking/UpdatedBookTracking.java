package by.meshicage.dto.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedBookTracking {
    private Long id;
    private Long bookId;
    private Boolean isBorrowed;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnBy;
    private Boolean isDeleted;
}
