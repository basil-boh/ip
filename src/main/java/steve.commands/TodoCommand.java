package steve.commands;

import steve.tasks.TaskManager;
import steve.tasks.ToDo;

// Executes todo tasks
public class TodoCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    public TodoCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String description = userInput.substring("todo".length()).trim();
        ToDo todo = new ToDo(description);
        taskManager.addTask(todo);
        todo.message();
    }
}