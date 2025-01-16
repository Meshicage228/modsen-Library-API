package by.meshicage.exception.impl;

public class DuplicateIsbnException extends RuntimeException {
    private static final String MESSAGE = "A book with ISBN %s already exists";

    public DuplicateIsbnException(String isbn) {
        super(String.format(MESSAGE, isbn));
    }
}
