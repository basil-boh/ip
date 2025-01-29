public class Messages {
    public static void greeting() {
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________");
    }

    public static void goodbye() {
        System.out.println("____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________");
    }

    public static void unknown() {
        String first = "Sorry I don't quite understand!";
        String second = " Please add a task by entering: \n 1. todo \n 2. deadline \n 3. event \n";
        String third = "Followed by a description of your task! =)";
        System.out.println(first + second + third);
    }

    public static String descriptionEmpty() {
        return "Description cannot be empty. Usage: todo <description>";
    }

    public static void border() {
        System.out.println("____________________________________________________________");
    }
    public static String formatExceptionMessage() {
        return "Please enter a valid number for the task!";
    }
}
