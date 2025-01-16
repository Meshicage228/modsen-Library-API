package by.meshicage.exception.impl.genre;

import by.meshicage.exception.ResourceNotFoundException;

public class GenreNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Genre with id %d not found";

    public GenreNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }
}
