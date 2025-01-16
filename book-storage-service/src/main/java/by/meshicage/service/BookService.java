package by.meshicage.service;

import by.meshicage.dto.book.*;
import org.springframework.data.domain.Page;

public interface BookService {
    CreatedBookDto createBook(CreateBookDto createBookDto);
    UpdatedBookDto fullUpdate(Long id, FullBookUpdateDto fullBookUpdateDto);
    UpdatedBookDto partUpdate(Long id, PartUpdateBookDto partUpdateBookDto);
    CreatedBookDto getBookById(Long id);
    CreatedBookDto getBookByISBN(String isbn);
    Page<CreatedBookDto> getAllBooks(Integer pageNumber, Integer pageSize);
    void deleteBookById(Long id);
}
