package steve.commands;
import steve.exceptions.InvalidTaskNumberException;

public interface Command {
    void execute() throws InvalidTaskNumberException, NumberFormatException;
}