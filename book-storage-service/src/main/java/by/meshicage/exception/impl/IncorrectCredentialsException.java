package by.meshicage.exception.impl;

public class IncorrectCredentialsException extends RuntimeException {
    private static final String message = "Incorrect credentials provided";

    public IncorrectCredentialsException() {
        super(message);
    }
}
