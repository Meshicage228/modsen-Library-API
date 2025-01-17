package by.meshicage.authenticationservice.exception.user;

import by.meshicage.authenticationservice.exception.abstr.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    private static final String message = "User with login : %s not found";

    public UserNotFoundException(String login) {
        super(String.format(message, login));
    }
}
