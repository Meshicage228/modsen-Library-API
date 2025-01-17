package by.meshicage.repository;

import by.meshicage.entity.BookTrackingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<BookTrackingEntity, Long>,
                                            PagingAndSortingRepository<BookTrackingEntity, Long> {
    Page<BookTrackingEntity> findByIsBorrowed(Boolean isBorrowed, Pageable pageable);
    Optional<BookTrackingEntity> findByBookId(Long bookId);
    void deleteByBookId(Long bookId);
}
