package steve.tasks;

import java.util.List;
import java.util.stream.Collectors;

import steve.ui.Messages;

/**
 * Represents a task that is used to search for tasks matching a given description.
 * This class filters tasks in the {@link TaskManager} based on a description
 * and displays matching tasks, if any.
 */
public class Find extends Task {
    private String description;
    private TaskManager taskManager;

    /**
     * Constructs a {@link Find} task with the given description and task manager.
     *
     * @param description The description used to filter tasks.
     * @param taskManager The TaskManager instance that holds the list of tasks.
     */
    public Find(String description, TaskManager taskManager) {
        super(description);
        this.description = description;
        this.taskManager = taskManager;
    }

    /**
     * Filters tasks in the task manager based on the description and prints
     * the matching tasks.
     * <p>
     * This method searches for tasks whose descriptions contain the given
     * search string (case-insensitive) and displays them. If no tasks match,
     * a message is displayed indicating no matches were found.
     */
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

    public String filterString() {
        List<Task> matchingTasks = taskManager.getTasks().stream()
                .filter(task -> task.getDescription()
                        .toLowerCase()
                        .contains(this.description.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            String s1 = ("______________________________\n");
            String s2 = ("     There are no matching tasks in your list:");
            String s3 = ("______________________________\n");
            return s1 + s2 + s3;
        } else {
            String s1 = ("______________________________\n");
            String s2 = ("Here are the matching tasks in your list: \n");
            String s3 = "";
            int i = 1;
            for (Task task : matchingTasks) {
                s3 = s3 + i + task.list() + "\n";
                i++;
            }
            String last = ("______________________________\n");
            return s1 + s2 + s3 + "\n" + last;
        }
    }
}

