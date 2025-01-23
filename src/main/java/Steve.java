import java.util.Scanner;

public class Steve{
    public static void main(String[] args) {
        Task[] userData = new Task[100];
        int taskCount = 0;
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Steve\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            String firstWord = userInput.split(" ")[0];

            if (firstWord.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0,j =0; i < taskCount; i++) {
                    System.out.println(++j + "." + " [" + userData[i].getStatusIcon() + "] " + userData[i].getDescription());

                }
                System.out.println("____________________________________________________________");
                userInput = scanner.nextLine();
            }
            else if (firstWord.equals("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                taskNumber--;
                userData[taskNumber].markAsDone();
                System.out.println(userData[taskNumber].message(firstWord));
                userInput = scanner.nextLine();
            }

            else if (firstWord.equals("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                taskNumber--;
                userData[taskNumber].markAsUndone();
                System.out.println(userData[taskNumber].message(firstWord));
                userInput = scanner.nextLine();
            }
            else {
                Task t = new Task(userInput);
                System.out.println("____________________________________________________________\n" +
                        "     \n" + "added: " + t.getDescription() + "\n" +
                        "____________________________________________________________");
                userData[taskCount++] = t;
                userInput = scanner.nextLine();
            }
        }
        System.out.println("____________________________________________________________\n" +
                "     \n" + "Bye! Hope to see you again soon =)" + "\n" +
                "____________________________________________________________");
    }
}
