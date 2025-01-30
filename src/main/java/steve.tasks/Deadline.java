package steve.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Deadline task
public class Deadline extends Task {
    protected String description;
    protected LocalDateTime by;
    protected boolean isValid = false;

    public Deadline(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: deadline <description /by yyyy-MM-dd HHmm>"
                : description);

        if (description != null && !description.isEmpty()) {
            String[] parts = description.split("/by");
            if (parts.length == 2) {
                this.description = parts[0].trim();
                String dateTimeString = parts[1].trim();
                try {
                    // Parse the date and time
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    this.by = LocalDateTime.parse(dateTimeString, formatter);
                    this.isValid = true;
                } catch (DateTimeParseException e) {
                    this.description = "Invalid date/time format. Usage: deadline <description /by yyyy-MM-dd HHmm>";
                    super.decreaseTaskCount();
                }
            } else {
                this.description = "Invalid format. Usage: deadline <description /by yyyy-MM-dd HHmm>";
                super.decreaseTaskCount();
            }
        } else {
            this.description = "Description cannot be empty. Usage: deadline <description /by yyyy-MM-dd HHmm>";
            super.decreaseTaskCount();
        }
    }

    public void message() {
        String result = this.description.startsWith("Invalid") || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][ ] " + description + " (By: " + formatDateTime(by) + ") \n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(result);
    }

    @Override
    public String getDescription() {
        String result;
        if (this.by != null) {
            result = this.description + " (By: " + formatDateTime(this.by) + ") ";
        } else {
            result = this.description;
        }
        return result;
    }

    @Override
    public String getOriginalDescription() {
        return this.description + " /by " + (this.by != null ? this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) : "");
    }

    @Override
    public String code() {
        return "D";
    }

    @Override
    public String type() {
        return "Deadline: ";
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }
}