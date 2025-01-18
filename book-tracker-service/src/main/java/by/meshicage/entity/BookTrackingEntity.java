package by.meshicage.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books_tracking")
@SQLDelete(sql = "UPDATE books_tracking SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class BookTrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false, unique = true)
    private Long bookId;

    @Column(name = "is_borrowed", nullable = false)
    private Boolean isBorrowed = false;

    @Column(name = "borrowed_at")
    private LocalDateTime borrowedAt;

    @Column(name = "return_by")
    private LocalDateTime returnBy;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
