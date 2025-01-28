public class Deadline extends Task {
    protected String description;
    protected String by;
    protected boolean isValid = false;

    public Deadline(String description) {
        super(description == null || description.trim().isEmpty()
                ? "Description cannot be empty. Usage: deadline <description /By Sunday>"
                : description);
        if(description != null && !description.isEmpty()) {
            String[] parts = description.split("/by");
            this.description = parts[0].trim();
            this.by = parts[1].trim();
            this.isValid = true;
        }
        else {
            this.description = "Description cannot be empty. Usage: deadline <description /By day>";
            super.decreaseTaskCount();
        }
    }

    public void message() {
        String result = this.description.equals("Description cannot be empty. Usage: deadline <description /By day>")
                ?   this.description
                :   "____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [D][ ] " + description + " (By: " + this.by + ") \n" +
                    "     Now you have " + taskCount() + " tasks in the list.\n" +
                    "____________________________________________________________";
        System.out.println(result);
    }

    @Override
    public String getDescription() {
        String result;
        if (this.by != null) {
            result = this.description + " (By: " + this.by + ") ";
        }
        else {
            result = this.description;
        }
        return result;
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

}
