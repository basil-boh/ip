public class Event extends Task {
    protected String description;
    protected String from;
    protected String to;

    public Event(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: event <description /from Sunday /to Wed>"
                : description);
        if (description != null && !description.trim().isEmpty()) {
            String[] parts = description.split("/");
            this.description = parts[0].trim();
            this.from = parts[1].replace("from", "").trim();
            this.to = parts[2].replace("to", "").trim();
        }
        else {
            this.description = "Description cannot be empty. Usage: event <description /from Sunday /to Wed>";
            super.decreaseTaskCount();
        }
    }

    public String message() {
        return this.description.equals("Description cannot be empty. Usage: event <description /from Sunday /to Wed>")
                ? "Description cannot be empty. Usage: event <description /from Sunday /to Wed>"
                :   "____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [E][ ] " + description + " (From: " + this.from + " to: " + this.to + ") \n" +
                    "     Now you have " + taskCount() + " tasks in the list.\n" +
                    "____________________________________________________________";
    }

    @Override
    public String getDescription() {
        return this.description + " (From: " + this.from + " to: " + this.to + ") ";
    }

    @Override
    public String code() {
        return "E";
    }

}
