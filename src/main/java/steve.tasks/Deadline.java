package steve.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 * A Deadline task has a description and a date/time when the task is due.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String description;
    private LocalDateTime by;
    private boolean isValid = false;

    /**
     * Creates a new Deadline task with the specified description.
     * The description must include a "/by" followed by a date/time in the format "yyyy-MM-dd HHmm".
     *
     * @param description the description of the deadline task, including a due date/time
     */
    public Deadline(String description) {
        super(validateDescription(description));
        initializeDeadline(description);
    }

    /**
     * Initializes deadline objects
     * */
    public void initializeDeadline(String description) {
        try {
            messageFormatter(description);
            this.isValid = true;
        } catch (IllegalArgumentException | DateTimeParseException e) {
            this.description = invalidInputMessage();
            this.by = null;
            this.isValid = false;
        }
    }

    /**
     * Formats user input description
     */
    private void messageFormatter(String description) {
        String[] parts = descriptionParser(description);
        this.description = parts[0].trim();
        this.by = LocalDateTime.parse(parts[1].trim(), INPUT_FORMATTER);
    }

    /**
     * Parses user description input into String array of description and deadline
     */
    public static String[] descriptionParser(String input) {
        String description = validateDescription(input);
        String[] parts = description.split("/by", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException(invalidInputMessage());
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /**
     * Checks if string is empty and returns appropriate string message
     */
    private static String validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(emptyInputMessage());
        }
        return description;
    }

    /**
     * Returns a string indicating invalid input format
     */
    public static String invalidInputMessage() {
        return "Invalid format. Usage: deadline <description /by yyyy-MM-dd HHmm>";
    }

    /**
     * Returns a string indicating description is empty
     */
    public static String emptyInputMessage() {
        return "Description cannot be empty. Usage: deadline <description /by yyyy-MM-dd HHmm>";
    }

    /**
     * Returns a string message indicating Deadline task is successfully added
     */
    public String addTask() {
        return "______________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [D][ ] " + description + " (By: " + formatDateTime(by) + ") \n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "______________________________\n";
    }

    /**
     * Returns a boolean on whether the deadline object is empty or invalid
     */
    public boolean invalidCheck() {
        return this.description.startsWith("Invalid")
                || this.description.startsWith("Description cannot be empty");
    }

    /**
     * Displays a message confirming the creation of the Deadline task.
     * If the task's description or date/time is invalid, an error message is shown.
     */
    public void message() {
        String result = invalidCheck()
                ? this.description
                : addTask();
        System.out.println(result);
    }

    /**
     * Returns a string message confirming the creation of the Deadline task.
     * If the task's description or date/time is invalid, an error message is shown.
     */
    public String messageString() {
        return invalidCheck()
                ? this.description
                : addTask();
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
