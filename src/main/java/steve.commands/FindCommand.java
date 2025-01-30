package steve.commands;

import steve.tasks.Find;
import steve.tasks.TaskManager;

public class FindCommand implements Command {
    private TaskManager taskManager;
    private String keyword;

    public FindCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.keyword = userInput.substring("find".length()).trim();
    }

    @Override
    public void execute() {
        Find find = new Find(this.keyword, this.taskManager);
        find.filter();
    }
}
