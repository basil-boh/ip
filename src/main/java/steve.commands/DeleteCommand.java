package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

// Delete command that removes tasks from list
public class DeleteCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public DeleteCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.deleteTask(taskNumber);
    }
}