package steve.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command entered.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}