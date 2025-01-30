package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

// Unmarks tasks
public class UnmarkCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public UnmarkCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.markTask(taskNumber, false); // Mark as not done
    }
}