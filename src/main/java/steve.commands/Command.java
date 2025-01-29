public interface Command {
    void execute() throws InvalidTaskNumberException, NumberFormatException;
}