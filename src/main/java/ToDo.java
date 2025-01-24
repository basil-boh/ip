public class ToDo extends Task {
    protected String description;

    public ToDo(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: todo <description>"
                : description);
        if (description == null || description.trim().isEmpty()) {
            this.description = "Description cannot be empty. Usage: todo <description>";
            super.decreaseTaskCount();
        }
        else {
            this.description = description;
        }
    }

    public String message() {
        return !this.description.equals("Description cannot be empty. Usage: todo <description>") ? "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] " + description + "\n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________" : "Description cannot be empty. Usage: todo <description>";
    }

    @Override
    public String code() {
        return "T";
    }


}
