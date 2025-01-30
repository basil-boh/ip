package steve.tasks;

import steve.ui.Messages;

import java.util.List;
import java.util.stream.Collectors;

public class Find extends Task {
    String description;
    TaskManager taskManager;

    public Find(String description, TaskManager taskManager) {
        super(description);
        this.description = description;
        this.taskManager = taskManager;
    }

    public void filter() {
        List<Task> matchingTasks = taskManager.getTasks().stream()
                .filter(task -> task.getDescription()
                        .toLowerCase()
                            .contains(this.description.toLowerCase()))
                                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println("     There are no matching tasks in your list:");
            System.out.println("____________________________________________________________");
        } else {
            Messages.border();
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            for (Task task : matchingTasks) {
                System.out.println(i + task.list());
                i++;
            }
            Messages.border();
        }
    }

}
