package by.meshicage.controller;

import by.meshicage.dto.book.*;
import by.meshicage.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/books")
public class BookController implements BookControllerDoc {
    private final BookService bookService;

    public CreatedBookDto saveBook(CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);
    }

    public CreatedBookDto getBook(Long id) {
        return bookService.getBookById(id);
    }

    public Page<CreatedBookDto> getAllBooks(Integer pageNumber, Integer pageSize) {
        return bookService.getAllBooks(pageNumber, pageSize);
    }

    public CreatedBookDto getBook(String ISBN) {
        return bookService.getBookByISBN(ISBN);
    }

    public UpdatedBookDto fullBookUpdate(Long id, FullBookUpdateDto bookUpdateDto) {
        return bookService.fullUpdate(id, bookUpdateDto);
    }

    public UpdatedBookDto partBookUpdate(Long id, PartUpdateBookDto partUpdateBookDto) {
        return bookService.partUpdate(id, partUpdateBookDto);
    }

    public void deleteBook(Long id) {
        bookService.deleteBookById(id);
    }
}
