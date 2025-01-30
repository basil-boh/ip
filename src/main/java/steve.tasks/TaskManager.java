package steve.tasks;
import steve.exceptions.InvalidTaskNumberException;

import java.util.ArrayList;
import java.util.List;

// Manages task
public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task.isValid()) {
            tasks.add(task);
        }
    }

    public void markTask(int taskNumber, boolean isDone) throws InvalidTaskNumberException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            String result = isDone ? "mark" : "unmark";
            task.markDoneOrNot(isDone);
            task.message(result);
        } else {
            throw new InvalidTaskNumberException("Invalid task number: " + taskNumber);
        }
    }

    public void deleteTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
        } else {
            throw new InvalidTaskNumberException("Invalid task number: " + taskNumber);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

}