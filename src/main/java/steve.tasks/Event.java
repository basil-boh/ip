package steve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start time and an end time.
 * This task includes a description and two dates: a start time ("from") and an end time ("to").
 * The task ensures that the description is not empty and the date/time format is valid.
 */
public class Event extends Task {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isValid = false;

    /**
     * Constructs an Event task with the specified description.
     *
     * @param description The description of the event task, including the dates.
     */
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
                    this.description = "Invalid date/time format. Usage: "
                            + "event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
                    super.decreaseTaskCount();
                }
            } else {
                this.description = "Invalid format. Usage: "
                        + "event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
                super.decreaseTaskCount();
            }
        } else {
            this.description = "Description cannot be empty. Usage: "
                    + "event <description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm>";
            super.decreaseTaskCount();
        }
    }

    /**
     * Displays a message about the task, including its description and the date range.
     * If the task description is valid, it will print the event task details,
     * including the start and end times. If the description is invalid,
     * an error message is displayed instead.
     */
    public void message() {
        String result = this.description.startsWith("Invalid")
                || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] " + description + " (From: " + formatDateTime(from)
                + " to: " + formatDateTime(to) + ") \n"
                + "     Now you have " + taskCount() + " tasks in the list.\n"
                + "____________________________________________________________";
        System.out.println(result);
    }

    /**
     * Returns a string representation of the event task, including its description
     * and date range.
     *
     * @return A string describing the event task, including the start and end times.
     */
    @Override
    public String toString() {
        String result = this.description.startsWith("Invalid")
                || this.description.startsWith("Description cannot be empty")
                ? this.description
                : "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] " + description + " (From: " + formatDateTime(from)
                + " to: " + formatDateTime(to) + ") \n"
                + "     Now you have " + taskCount() + " tasks in the list.\n"
                + "____________________________________________________________";
        return result;
    }

    /**
     * Returns the description of the event task, including the start and end times.
     *
     * @return A string describing the event with its date range.
     */
    @Override
    public String getDescription() {
        return this.description + " (From: " + formatDateTime(from) + " to: " + formatDateTime(to) + ") ";
    }

    /**
     * Returns the code representing the event task type.
     *
     * @return A string representing the event task type ("E").
     */
    @Override
    public String code() {
        return "E";
    }

    /**
     * Returns whether the event task is valid based on its description and dates.
     *
     * @return True if the task is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        return isValid;
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type ("Event: ").
     */
    @Override
    public String type() {
        return "Event: ";
    }

    /**
     * Returns the original description, including the dates in the format used to create the task.
     *
     * @return A string containing the original description, including the "/from" and "/to" date/times.
     */
    @Override
    public String getOriginalDescription() {
        return this.description + " /from " + (from != null
                ? from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                : "") + " /to " + (to != null
                ? to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                : "");
    }

    /**
     * Formats the given {@link LocalDateTime} into a more readable string format.
     *
     * @param dateTime The {@link LocalDateTime} to format.
     * @return A string representing the formatted date/time.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }
}
