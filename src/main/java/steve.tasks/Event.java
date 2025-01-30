package steve.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Event task
public class Event extends Task {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected boolean isValid = false;

    public Event(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>"
                : description);

        if (description != null && !description.trim().isEmpty()) {
            String[] parts = description.split("/");
            if (parts.length == 3) {
                this.description = parts[0].trim();
                String fromString = parts[1].replace("from", "").trim();
                String toString = parts[2].replace("to", "").trim();

                try {
                    // Parse the start and end dates/times
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    this.from = LocalDateTime.parse(fromString, formatter);
                    this.to = LocalDateTime.parse(toString, formatter);
                    this.isValid = true;
                } catch (DateTimeParseException e) {
                    this.description = "Invalid date/time format. Usage: event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
                    super.decreaseTaskCount();
                }
            } else {
                this.description = "Invalid format. Usage: event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
                super.decreaseTaskCount();
            }
        } else {
            this.description = "Description cannot be empty. Usage: event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
            super.decreaseTaskCount();
        }
    }

    public void message() {
        String result = this.description.startsWith("Invalid") || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][ ] " + description + " (From: " + formatDateTime(from) + " to: " + formatDateTime(to) + ") \n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(result);
    }

    @Override
    public String toString() {
        String result = this.description.startsWith("Invalid") || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][ ] " + description + " (From: " + formatDateTime(from) + " to: " + formatDateTime(to) + ") \n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";
        return result;
    }

    @Override
    public String getDescription() {
        return this.description + " (From: " + formatDateTime(from) + " to: " + formatDateTime(to) + ") ";
    }

    @Override
    public String code() {
        return "E";
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String type() {
        return "Event: ";
    }

    @Override
    public String getOriginalDescription() {
        return this.description + " /from " + (from != null ? from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) : "") +
                " /to " + (to != null ? to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) : "");
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }
}