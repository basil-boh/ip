import java.util.Scanner;

public class Steve {
    public static void main(String[] args) {
        String[] userData = new String[100];
        int taskCount = 0;
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0,j =0; i < taskCount; i++) {
                    System.out.println(++j + ": " + userData[i]);

                }
                System.out.println("____________________________________________________________");
                userInput = scanner.nextLine();
            }
            else {
                System.out.println("____________________________________________________________\n" +
                        "     \n" + "added: " + userInput + "\n" +
                        "____________________________________________________________");
                userData[taskCount++] = userInput;
                userInput = scanner.nextLine();
            }
        }
        System.out.println("____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________");
    }
}
