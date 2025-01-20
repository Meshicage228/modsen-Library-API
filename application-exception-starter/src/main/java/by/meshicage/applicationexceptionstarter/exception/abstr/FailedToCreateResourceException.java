package by.meshicage.applicationexceptionstarter.exception.abstr;

public abstract class FailedToCreateResourceException extends RuntimeException {
    public FailedToCreateResourceException(String message) {
        super(message);
    }
}
