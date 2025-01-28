public class ToDo extends Task {
    protected String description;
    protected boolean isValid = false;

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
            this.isValid = true;
        }
    }

    public void message() {
        String result = !this.description.equals("Description cannot be empty. Usage: todo <description>") ? "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] " + description + "\n" +
                "     Now you have " + taskCount() + " tasks in the list.\n" +
                "____________________________________________________________" : "Description cannot be empty. Usage: todo <description>";
        System.out.println(result);
    }

    @Override
    public String code() {
        return "T";
    }

    @Override
    public boolean isValid() {

        return isValid;
    }

    @Override
    public String type() {
        return "Todo: ";
    }

    @Override
    public String getOriginalDescription() {
        return this.description;
    }

}
