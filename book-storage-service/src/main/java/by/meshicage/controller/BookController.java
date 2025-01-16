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

    public CreatedBookDto saveBook(@RequestBody CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);
    }

    public CreatedBookDto getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    public Page<CreatedBookDto> getAllBooks(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                            @RequestParam(defaultValue = "100", required = false) Integer pageSize) {
        return bookService.getAllBooks(pageNumber, pageSize);
    }

    public CreatedBookDto getBook(@RequestParam("isbn") String ISBN) {
        return bookService.getBookByISBN(ISBN);
    }

    public UpdatedBookDto fullBookUpdate(@PathVariable Long id,
                                         @RequestBody FullBookUpdateDto bookUpdateDto) {
        return bookService.fullUpdate(id, bookUpdateDto);
    }

    public UpdatedBookDto partBookUpdate(@PathVariable Long id,
                                         @RequestBody PartUpdateBookDto partUpdateBookDto) {
        return bookService.partUpdate(id, partUpdateBookDto);
    }

    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
