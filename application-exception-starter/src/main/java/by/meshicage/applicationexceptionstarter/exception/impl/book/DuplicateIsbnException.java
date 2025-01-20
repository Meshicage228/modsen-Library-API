package by.meshicage.applicationexceptionstarter.exception.impl.book;

public class DuplicateIsbnException extends RuntimeException {
    private static final String MESSAGE = "A book with ISBN %s already exists";

    public DuplicateIsbnException(String isbn) {
        super(String.format(MESSAGE, isbn));
    }
}
