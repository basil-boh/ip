import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean done;
    protected static int taskCount = 0;
    private static final String FILE_PATH = "src/main/data/steve.txt";

    public Task(String description) {
        this.description = description;
        this.done = false;
        taskCount++;
    }

    public String code() {
        return "Task";
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void message(String word) {
        String result = word.equals("mark")
                ? "____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       [X] " + this.description + "\n" +
                "____________________________________________________________"
                : "____________________________________________________________\n" +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + this.description + "\n" +
                "____________________________________________________________";
        System.out.println(result);
    }

    public void markDoneOrNot(boolean isDone) {
        this.done = isDone;
    }

    public int taskCount() {
        return taskCount;
    }
    public void decreaseTaskCount() {
        taskCount--;
    }

    public String list() {
        return "." + " [" + this.code() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void delete() {
        taskCount--;
        String result = "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [" + this.code() + "]" + "[" + this.getStatusIcon() + "] " + this.getOriginalDescription() + "\n" +
                "     Now you have " + taskCount + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(result);
    }

    public static void validateTaskNumberMark(int taskNumber, int taskCount, ArrayList<Task> userData, CommandType command, String firstWord) throws InvalidTaskNumberException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidTaskNumberException(
                    "Task number " + taskNumber + " is invalid. Please enter a number within Task Count Range: " + taskCount
            );
        }
        else {
            Task task = userData.get(taskNumber - 1);
            task.markDoneOrNot(command == CommandType.mark);
            task.message(firstWord);
        }
    }
    public static void validateTaskNumberDel(int taskNumber, int taskCount, ArrayList<Task> userData) throws InvalidTaskNumberException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidTaskNumberException(
                    "Task number " + taskNumber + " is invalid. Please enter a number within Task Count Range: " + taskCount
            );
        }
        else {
            Task deletedTask = userData.remove(taskNumber - 1);
            deletedTask.delete();
        }
    }

    public String type() {
        return "Task: ";
    }

    public boolean isValid() {
        return false;
    }

    public String getOriginalDescription() {
        return this.description;
    }

    public static void saveTasks(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(task.type() + task.getOriginalDescription());
                writer.newLine(); // Add a new line after each task
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
