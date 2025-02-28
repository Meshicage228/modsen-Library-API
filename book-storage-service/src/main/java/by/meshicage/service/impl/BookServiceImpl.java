package by.meshicage.service.impl;

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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final KafkaProducer kafkaProducer;
    private final BookMapper bookMapper;

    @Override
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
    @Transactional
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
    @Transactional
    public UpdatedBookDto partUpdate(Long id, PartUpdateBookDto partUpdateBookDto) {
        return bookRepository.findById(id)
                .map(bookEntity -> bookMapper.partBookUpdate(bookEntity, partUpdateBookDto))
                .map(bookMapper::toUpdatedBookDto)
                .orElseThrow(() -> new BookUpdateException(id));
    }

    @Override
    public CreatedBookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toCreatedBookDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
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
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        kafkaProducer.deleteBookTracking(id);
    }
}
