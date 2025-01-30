package steve.commands;
import steve.exceptions.InvalidTaskNumberException;

//Command Interface that executes different tasks
public interface Command {
    void execute() throws InvalidTaskNumberException, NumberFormatException;
}