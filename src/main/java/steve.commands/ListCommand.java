package steve.commands;
import steve.tasks.Task;
import steve.tasks.TaskManager;
import steve.ui.Messages;

import java.util.List;

// Displays list of tasks
public class ListCommand implements Command {
    private TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

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