package by.meshicage.applicationexceptionstarter.exception.impl.role;

import by.meshicage.applicationexceptionstarter.exception.abstr.ResourceNotFoundException;

public class RoleNotFountException extends ResourceNotFoundException {
    private static final String message = "Role not found";

    public RoleNotFountException() {
        super(message);
    }
}
