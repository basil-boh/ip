package steve.commands;

import steve.tasks.Event;
import steve.tasks.TaskManager;

// Executes Event tasks
public class EventCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public EventCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String description = userInput.substring("event".length()).trim();
        Event event = new Event(description);
        taskManager.addTask(event);
        event.message();
    }
}