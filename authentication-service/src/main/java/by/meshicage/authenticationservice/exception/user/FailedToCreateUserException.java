package by.meshicage.authenticationservice.exception.user;

import by.meshicage.authenticationservice.exception.abstr.FailedToCreateResourceException;

public class FailedToCreateUserException extends FailedToCreateResourceException {
    private static final String message = "Failed to create new user resource";

    public FailedToCreateUserException() {
        super(message);
    }
}
