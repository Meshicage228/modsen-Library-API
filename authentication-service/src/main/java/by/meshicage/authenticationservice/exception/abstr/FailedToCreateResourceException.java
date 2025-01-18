package by.meshicage.authenticationservice.exception.abstr;

public abstract class FailedToCreateResourceException extends RuntimeException {
    public FailedToCreateResourceException(String message) {
        super(message);
    }
}
