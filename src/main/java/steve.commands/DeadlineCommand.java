package steve.commands;

import steve.tasks.Deadline;
import steve.tasks.TaskManager;

public class DeadlineCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public DeadlineCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String description = userInput.substring("deadline".length()).trim();
        Deadline deadline = new Deadline(description);
        taskManager.addTask(deadline);
        deadline.message();
    }
}