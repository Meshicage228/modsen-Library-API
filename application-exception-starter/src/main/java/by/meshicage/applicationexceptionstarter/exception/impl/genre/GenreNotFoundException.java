package by.meshicage.applicationexceptionstarter.exception.impl.genre;

import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;

public class GenreNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Genre with id %d not found";

    public GenreNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }
}
