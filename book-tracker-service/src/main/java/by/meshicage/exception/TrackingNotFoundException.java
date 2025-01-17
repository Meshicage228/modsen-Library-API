package by.meshicage.exception;

public class TrackingNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "Tracking with book id : %d not found";

    public TrackingNotFoundException(Long id) {
        super(formatMessage(MESSAGE, id));
    }
}
