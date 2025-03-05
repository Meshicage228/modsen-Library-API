package by.meshicage.service.impl;

import by.meshicage.dto.book.*;
import by.meshicage.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
@Primary
public class BookServiceImpl implements BookService {
    private BookService cacheService;

    public BookServiceImpl(@Qualifier("bookCache") BookService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public CreatedBookDto createBook(CreateBookDto createBookDto) {
        return cacheService.createBook(createBookDto);
    }

    @Override
    @Transactional
    public UpdatedBookDto fullUpdate(Long id, FullBookUpdateDto fullBookUpdateDto) {
        return cacheService.fullUpdate(id, fullBookUpdateDto);
    }

    @Override
    @Transactional
    public UpdatedBookDto partUpdate(Long id, PartUpdateBookDto partUpdateBookDto) {
        return cacheService.partUpdate(id, partUpdateBookDto);
    }

    @Override
    public CreatedBookDto getBookById(Long id) {
        return cacheService.getBookById(id);
    }

    @Override
    public CreatedBookDto getBookByISBN(String isbn) {
        return cacheService.getBookByISBN(isbn);
    }

    @Override
    public Page<CreatedBookDto> getAllBooks(Integer pageNum, Integer pageSize) {
        return cacheService.getAllBooks(pageNum, pageSize);
    }

    @Override
    public void deleteBookById(Long id) {
        cacheService.deleteBookById(id);
    }
}
