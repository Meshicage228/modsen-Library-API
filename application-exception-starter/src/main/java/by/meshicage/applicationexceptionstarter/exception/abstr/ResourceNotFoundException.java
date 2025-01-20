package by.meshicage.applicationexceptionstarter.exception.abstr;

public abstract class ResourceNotFoundException extends RuntimeException {
    protected ResourceNotFoundException(String message) {
        super(message);
    }

    protected static String formatMessage(String template, Long id) {
        return String.format(template, id);
    }

    protected static String formatMessage(String template, String isbn) {
        return String.format(template, isbn);
    }
}
