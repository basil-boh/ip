package steve.commands;

import steve.exceptions.InvalidCommandException;
import steve.tasks.TaskManager;

// Creates different Command Objects based on user input
public class CommandCreate {
    public static Command createCommand(String userInput, TaskManager taskManager) throws InvalidCommandException {
        String firstWord = userInput.split(" ")[0];
        switch (firstWord) {
            case "list":
                return new ListCommand(taskManager);
            case "mark":
                return new MarkCommand(taskManager, userInput);
            case "unmark":
                return new UnmarkCommand(taskManager, userInput);
            case "todo":
                return new TodoCommand(taskManager, userInput);
            case "deadline":
                return new DeadlineCommand(taskManager, userInput);
            case "event":
                return new EventCommand(taskManager, userInput);
            case "delete":
                return new DeleteCommand(taskManager, userInput);
            default:
                throw new InvalidCommandException("Unknown command: " + firstWord);
        }
    }
}