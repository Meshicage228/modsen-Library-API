package by.meshicage.applicationexceptionstarter.exception.impl.book;

import by.meshicage.applicationexceptionstarter.exception.abstr.BookException;

public class BookUpdateException extends BookException {
    private static final String MESSAGE = "Exception during update of book entity with id %d";

    public BookUpdateException(Long id) {
        super(formatMessage(MESSAGE, id));
    }
}
