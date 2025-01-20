package by.meshicage.applicationexceptionstarter.exception.abstr;

public abstract class BookException extends RuntimeException {
    protected BookException(String message) {
        super(message);
    }

    protected static String formatMessage(String template, Long id) {
        return String.format(template, id);
    }

    protected static String formatMessage(String template, String message) {
        return String.format(template, message);
    }
}
