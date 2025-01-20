package by.meshicage.applicationexceptionstarter.exception.impl.user;

import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    private static final String message = "User with login : %s not found";

    public UserNotFoundException(String login) {
        super(String.format(message, login));
    }
}
