public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
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
    //...
}
