package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

// Marks Tasks
public class MarkCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public MarkCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.markTask(taskNumber, true); // Mark as done
    }


}