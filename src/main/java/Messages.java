class Messages {
    public void greeting() {
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________");
    }

    public String goodbye() {
        return "____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________";
    }

    public void unknown() {
        String first = "Sorry I don't quite understand!";
        String second = " Please add a task by entering: \n 1. todo \n 2. deadline \n 3. event \n";
        String third = "Followed by a description of your task! =)";
        System.out.println(first + second + third);
    }

    public String descriptionEmpty() {
        return "Description cannot be empty. Usage: todo <description>";
    }

    public void border() {
        System.out.println("____________________________________________________________");
    }
    public String formatExceptionMessage() {
        return "Please enter a valid number for the task!";
    }
}
