import java.util.Scanner;

public class Steve {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    "     \n" + userInput + "\n" +
                    "____________________________________________________________");
            userInput = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________");
    }
}
