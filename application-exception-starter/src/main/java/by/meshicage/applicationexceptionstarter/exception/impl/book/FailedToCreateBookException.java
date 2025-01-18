package by.meshicage.applicationexceptionstarter.exception.impl.book;

import by.meshicage.applicationexceptionstarter.exception.abstr.BookException;

public class FailedToCreateBookException extends BookException {
    private static final String MESSAGE = "Failed to create book with title : %s";

    public FailedToCreateBookException(String bootTitle) {
        super(formatMessage(MESSAGE, bootTitle));
    }
}
