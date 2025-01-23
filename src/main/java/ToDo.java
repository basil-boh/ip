public class ToDo extends Task {
    protected String description;

    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    public String message() {
        return "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] " + description + "\n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";
    }

    @Override
    public String code() {
        return "T";
    }


}
