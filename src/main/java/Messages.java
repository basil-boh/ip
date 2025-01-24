class Messages {
    public String greeting() {
        return "____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
    }

    public String goodbye() {
        return "____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________";
    }

    public String unknown() {
        String first = "Sorry I don't quite understand!";
        String second = " Please add a task by entering: \n 1. todo \n 2. deadline \n 3. event \n";
        String third = "Followed by a description of your task! =)";
        return first + second + third;
    }

    public String descriptionEmpty() {
        return "Description cannot be empty. Usage: todo <description>";
    }

    public String border() {
        return "____________________________________________________________";
    }

}
