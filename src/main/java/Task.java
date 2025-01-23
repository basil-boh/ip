public class Task {
    protected String description;
    protected boolean done;
    protected static int taskCount = 0;

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

    public void markAsDone() {
        this.done = true;
    }

    public String message(String word) {
        return word.equals("mark")
                ? "____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       [X] " + this.description + "\n" +
                "____________________________________________________________"
                : "____________________________________________________________\n" +
                "     OK, I've marked this task as not done yet:\n" +
                "       [ ] " + this.description + "\n" +
                "____________________________________________________________";
    }

    public void markAsUndone() {
        this.done = false;
    }

    public int taskCount() {
        return taskCount;
    }

    public String list() {
        return "." + " [" + this.code() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String delete() {
        taskCount--;
        return "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [" + this.code() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + "\n" +
                "     Now you have " + taskCount + " tasks in the list.\n" +
                "____________________________________________________________";
    }
    //...
}
