package by.meshicage.authenticationservice.exception.role;

public class RoleNotFountException extends RuntimeException {
    private static final String message = "Role not found";

    public RoleNotFountException() {
        super(message);
    }
}
