package steve.commands;

import java.util.List;

import steve.tasks.Task;
import steve.tasks.TaskManager;
import steve.ui.Messages;

/**
 * Represents a command that displays the list of tasks in the task manager.
 */
public class ListCommand implements Command {
    private TaskManager taskManager;

    /**
     * Constructs a ListCommand with the specified task manager.
     *
     * @param taskManager The task manager that manages the list of tasks.
     */
    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the list command by retrieving and displaying all tasks.
     */
    @Override
    public void execute() {
        List<Task> tasks = taskManager.getTasks();
        Messages.border();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + tasks.get(i).list());
        }
        Messages.border();
    }
}
