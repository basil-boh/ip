public class Event extends Task {
    protected String description;
    protected String from;
    protected String to;

    public Event(String description) {
        super(description);
        String[] parts = description.split("/");
        this.description = parts[0].trim();
        this.from = parts[1].replace("from", "").trim();
        this.to = parts[2].replace("to", "").trim();
    }

    public String message() {
        return "____________________________________________________________\n" +
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
