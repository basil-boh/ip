public class Deadline extends Task {
    protected String description;
    protected String by;

    public Deadline(String description) {
        super(description);
        String[] parts = description.split("/by");
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    public String message() {
        return "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][ ] " + description + " (By: " + this.by + ") \n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";
    }

    @Override
    public String getDescription() {
        return this.description + " (By: " + this.by + ") ";
    }

    @Override
    public String code() {
        return "D";
    }

}
