package by.reengineering.agency.services.exception;

public class InvalidUserNameOrPasswordException extends IllegalArgumentException {
    public InvalidUserNameOrPasswordException() {
        super("Invalid login or password");
    }
}