package by.meshicage.applicationexceptionstarter.exception.impl.book;

import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;

public class BookNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Book with id %d not found";

    public BookNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }

    public BookNotFoundException(String ISBN) {
        super(formatMessage(MESSAGE, ISBN));
    }
}
