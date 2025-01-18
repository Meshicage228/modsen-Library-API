package by.meshicage.authenticationservice.exception.user;

public class IncorrectCredentialsException extends RuntimeException {
    private static final String message = "Incorrect credentials provided";

    public IncorrectCredentialsException() {
        super(message);
    }
}
