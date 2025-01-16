package by.meshicage.exception;

public abstract class BookException extends RuntimeException {
    protected BookException(String message) {
        super(message);
    }

    protected static String formatMessage(String template, Long id) {
        return String.format(template, id);
    }
}
