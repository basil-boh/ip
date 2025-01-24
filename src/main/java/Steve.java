import java.util.ArrayList;
import java.util.Scanner;

public class Steve{
    public static void main(String[] args) {
        // Initialising objects & variables 
            Messages message = new Messages();
            ArrayList<Task> userData = new ArrayList<>();
            message.greeting();
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String restOfLine;
            CommandType command;

            while (!userInput.equals("bye")) { //Exits on user command bye
                try {
                    String firstWord = userInput.split(" ")[0];

                    try { //Detects command (first word) -> assigns command as "unknown" if not a valid command
                        command = CommandType.valueOf(firstWord);
                    } catch (IllegalArgumentException e) {
                        command = CommandType.unknown;
                    }

                    switch (command) { //Command: list
                        case list -> {
                            printList(message, userData); //Prints out list of tasks followed by next user command
                            userInput = scanner.nextLine();
                        }
                        case mark, unmark-> { //Command: mark/unmark
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            //Validates marking/unmarking of task & proceeds if valid
                            Task.validateTaskNumberMark(taskNumber, userData.size(), userData, command, firstWord);
                            userInput = scanner.nextLine();
                        }
                        case todo -> { //Command: todo
                            //Reads rest of line after command keyword
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            ToDo t = new ToDo(restOfLine); //Creates todo object with using description
                            t.message(); //Prints object message
                            addTask(userData, t); //Adds task to Task ArrayList
                            userInput = scanner.nextLine();
                        }
                        case deadline -> {
                            //Reads rest of line after command keyword
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Deadline d = new Deadline(restOfLine); //Creates deadline object with using description
                            d.message(); //Prints object message
                            addTask(userData, d); //Adds tasks to Task ArrayList
                            userInput = scanner.nextLine();
                        }
                        case event -> {
                            //Reads rest of line after command keyword
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Event e = new Event(restOfLine); //Creates deadline object with using description
                            e.message(); //Prints object message
                            addTask(userData, e); //Prints object message
                            userInput = scanner.nextLine();
                        }
                        case delete -> { //Command: delete
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            //Validates deletion of task & proceeds if valid
                            Task.validateTaskNumberDel(taskNumber, userData.size(), userData);
                            userInput = scanner.nextLine();
                        }
                        case unknown -> { //Command: unknown
                            message.unknown(); //Prints invalid command message & takes in next user command
                            userInput = scanner.nextLine();
                        }
                    }
                }
                catch (InvalidTaskNumberException e) { //E.g. Catches invalid inputs like entering number 0 < or > taskCount
                    System.out.println(e.getMessage());
                    userInput = scanner.nextLine();
                }
                catch (NumberFormatException e) { //E.g. Catches invalid inputs like mark/unmark abc
                    System.out.println(message.formatExceptionMessage());
                    userInput = scanner.nextLine();
                }
            }
            System.out.println(message.goodbye());
    }
    public static void printList(Messages message, ArrayList<Task> userData) {
        message.border();
        for (int i = 0, j = 0; i < userData.size(); i++) {
            Task currTask = userData.get(i);
            System.out.println(++j + currTask.list());
        }
        message.border();
    }

    private static void addTask(ArrayList<Task> userData, Task task) {
        if (task.isValid()) {
            userData.add(task);
        }
    }
}

