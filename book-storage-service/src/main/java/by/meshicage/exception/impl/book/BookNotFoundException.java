package by.meshicage.exception.impl.book;

import by.meshicage.exception.ResourceNotFoundException;

public class BookNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Book with id %d not found";

    public BookNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }

    public BookNotFoundException(String ISBN) {
        super(formatMessage(MESSAGE, ISBN));
    }
}
