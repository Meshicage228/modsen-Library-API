package by.meshicage.repository;

import by.meshicage.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>,
                                        PagingAndSortingRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);
}
