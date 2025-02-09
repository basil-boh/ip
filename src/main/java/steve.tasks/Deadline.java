package steve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 * A Deadline task has a description and a date/time when the task is due.
 */
public class Deadline extends Task {
    protected String description;
    protected LocalDateTime by;
    protected boolean isValid = false;

    /**
     * Creates a new Deadline task with the specified description.
     * The description must include a "/by" followed by a date/time in the format "yyyy-MM-dd HHmm".
     *
     * @param description the description of the deadline task, including a due date/time
     */
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

    /**
     * Displays a message confirming the creation of the Deadline task.
     * If the task's description or date/time is invalid, an error message is shown.
     */
    public void message() {
        String result = this.description.startsWith("Invalid")
                || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [D][ ] " + description + " (By: " + formatDateTime(by) + ") \n"
                + "     Now you have " + taskCount() + " tasks in the list.\n"
                + "____________________________________________________________";
        System.out.println(result);
    }

    public String messageString() {
        String result = this.description.startsWith("Invalid")
                || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [D][ ] " + description + " (By: " + formatDateTime(by) + ") \n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "____________________________________________________________";
        return result;
    }

    /**
     * Returns the description of the Deadline task.
     * If the deadline date/time is set, it is included in the description.
     *
     * @return the description of the task, including the date/time if available
     */
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

    /**
     * Returns the original description of the Deadline task, including the due date/time.
     *
     * @return the original description with the "/by" date/time format
     */
    @Override
    public String getOriginalDescription() {
        return this.description + " /by " + (this.by != null
                ? this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                : "");
    }

    /**
     * Returns the code that represents a Deadline task.
     *
     * @return the code for a Deadline task, which is "D"
     */
    @Override
    public String code() {
        return "D";
    }

    /**
     * Returns the task type as a string.
     *
     * @return the type of the task, which is "Deadline: "
     */
    @Override
    public String type() {
        return "Deadline: ";
    }

    /**
     * Returns whether the Deadline task is valid (i.e., the description and date/time are correctly parsed).
     *
     * @return true if the task is valid, false otherwise
     */
    @Override
    public boolean isValid() {
        return isValid;
    }

    /**
     * Formats a LocalDateTime object into a human-readable string.
     *
     * @param dateTime the LocalDateTime object to format
     * @return a formatted string representing the date/time in "MMM dd yyyy, hh:mm a" format
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }
}
