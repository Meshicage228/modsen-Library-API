package by.meshicage.applicationexceptionstarter.exception.impl.user;

import by.meshicage.applicationexceptionstarter.exception.abstr.FailedToCreateResourceException;

public class FailedToCreateUserException extends FailedToCreateResourceException {
    private static final String MESSAGE = "Failed to create new user resource";

    public FailedToCreateUserException() {
        super(MESSAGE);
    }
}
