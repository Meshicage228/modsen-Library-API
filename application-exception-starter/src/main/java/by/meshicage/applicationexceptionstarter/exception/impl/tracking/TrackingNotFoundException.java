package by.meshicage.applicationexceptionstarter.exception.impl.tracking;

import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;

public class TrackingNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Tracking with book id : %d not found";

    public TrackingNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }
}
