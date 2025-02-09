package steve.tasks;

/**
 * Represents a Todo task.
 * This class is used to handle tasks of type "Todo" with a description and their status.
 */
public class ToDo extends Task {
    private String description;
    private boolean isValid = false;

    /**
     * Constructs a ToDo task with the specified description.
     * If the description is empty, sets a default error message and decreases the task count.
     *
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: todo <description>"
                : description);
        if (description == null || description.trim().isEmpty()) {
            this.description = "Description cannot be empty. Usage: todo <description>";
            super.decreaseTaskCount();
        } else {
            this.description = description;
            this.isValid = true;
        }
    }

    /**
     * Returns the description of the ToDo task.
     *
     * @return the description of the ToDo task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Prints a message indicating that the ToDo task has been added successfully,
     * or prints an error message if the description is empty.
     */
    public void message() {
        String result = !this.description.equals("Description cannot be empty. "
                + "Usage: todo <description>")
                ? "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] " + description + "\n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "____________________________________________________________"
                : "Description cannot be empty. Usage: todo <description>";
        System.out.println(result);
    }

    public String messageString() {
        String result = !this.description.equals("Description cannot be empty. "
                + "Usage: todo <description>")
                ? "______________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] " + description + "\n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "______________________________"
                : "Description cannot be empty. Usage: todo <description>";
        return (result);
    }

    /**
     * Returns a string representation of the ToDo task, including the task's
     * description and its current status.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return !this.description.equals("Description cannot be empty. Usage: "
                + "todo <description>")
                ? "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] " + description + "\n"
                + "     Now you have " + taskCount() + " tasks in the list.\n"
                + "____________________________________________________________"
                : "Description cannot be empty. Usage: todo <description>";
    }

    /**
     * Returns the code representing this type of task.
     * For a ToDo task, it returns "T".
     *
     * @return the code for a ToDo task
     */
    @Override
    public String code() {
        return "T";
    }

    /**
     * Returns whether the task is valid. A ToDo task is valid if the description
     * is not empty.
     *
     * @return true if the task is valid, false otherwise
     */
    @Override
    public boolean isValid() {

        return isValid;
    }

    /**
     * Returns the type of task as a string, which is "Todo".
     *
     * @return the type of task
     */
    @Override
    public String type() {
        return "Todo: ";
    }

    /**
     * Returns the original description of the ToDo task.
     *
     * @return the original description of the ToDo task
     */
    @Override
    public String getOriginalDescription() {
        return this.description;
    }
}

