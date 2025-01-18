package by.meshicage.applicationexceptionstarter.exception.impl.tracking;

import by.meshicage.applicationexceptionstarter.exception.abstr.FailedToCreateResourceException;

public class FailedToCreateTrackingException extends FailedToCreateResourceException {
    private static final String MESSAGE = "Failed to create new user resource";

    public FailedToCreateTrackingException() {
        super(MESSAGE);
    }
}
