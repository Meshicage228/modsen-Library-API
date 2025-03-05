package by.meshicage.service.cache;

import by.meshicage.applicationexceptionstarter.exception.impl.book.BookNotFoundException;
import by.meshicage.applicationexceptionstarter.exception.impl.book.BookUpdateException;
import by.meshicage.applicationexceptionstarter.exception.impl.book.FailedToCreateBookException;
import by.meshicage.dto.book.*;
import by.meshicage.entity.BookEntity;
import by.meshicage.entity.GenreEntity;
import by.meshicage.kafka.KafkaProducer;
import by.meshicage.mapper.BookMapper;
import by.meshicage.repository.BookRepository;
import by.meshicage.service.BookService;
import by.meshicage.service.GenreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("bookCache")
public class BookCacheService implements BookService {
    private BookRepository bookRepository;
    private GenreService genreService;
    private KafkaProducer kafkaProducer;
    private BookMapper bookMapper;

    public BookCacheService(BookRepository bookRepository,
                            @Qualifier("genreCacheService") GenreService genreService,
                            KafkaProducer kafkaProducer,
                            BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.kafkaProducer = kafkaProducer;
        this.bookMapper = bookMapper;
    }

    @Override
    @CachePut(value = "books", key = "#result.id")
    public CreatedBookDto createBook(CreateBookDto createBookDto) {
        return Optional.of(bookMapper.toBookEntity(createBookDto))
                .map(bookEntity -> {
                    GenreEntity byId = genreService.findById(createBookDto.getGenre().getId());
                    bookEntity.setGenre(byId);
                    return bookEntity;
                })
                .map(bookEntity -> {
                    BookEntity saved = bookRepository.save(bookEntity);
                    kafkaProducer.createBookTracking(saved.getId());
                    return saved;
                })
                .map(bookMapper::toCreatedBookDto)
                .orElseThrow(() -> new FailedToCreateBookException(createBookDto.getTitle()));
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "books", key = "#result.id"),
                    @CachePut(value = "books", key = "#result.isbn")
            })
    public UpdatedBookDto fullUpdate(Long id, FullBookUpdateDto fullBookUpdateDto) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    GenreEntity byId = genreService.findById(fullBookUpdateDto.getGenre().getId());
                    bookEntity.setGenre(byId);
                    return bookMapper.fullBookUpdate(bookEntity, fullBookUpdateDto);
                })
                .map(bookMapper::toUpdatedBookDto)
                .orElseThrow(() -> new BookUpdateException(id));
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "books", key = "#result.id"),
                    @CachePut(value = "books", key = "#result.isbn")
            })
    public UpdatedBookDto partUpdate(Long id, PartUpdateBookDto partUpdateBookDto) {
        return bookRepository.findById(id)
                .map(bookEntity -> bookMapper.partBookUpdate(bookEntity, partUpdateBookDto))
                .map(bookMapper::toUpdatedBookDto)
                .orElseThrow(() -> new BookUpdateException(id));
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public CreatedBookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toCreatedBookDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    @Cacheable(value = "books", key = "#isbn")
    public CreatedBookDto getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toCreatedBookDto)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public Page<CreatedBookDto> getAllBooks(Integer pageNum, Integer pageSize) {
        return bookRepository.findAll(PageRequest.of(pageNum, pageSize))
                .map(bookMapper::toCreatedBookDto);
    }

    @Override
    @CacheEvict(value = "books", key = "#result.id")
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        kafkaProducer.deleteBookTracking(id);
    }
}
