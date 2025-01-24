import java.util.ArrayList;
import java.util.Scanner;

public class Steve{
    public static void main(String[] args) {
            Messages message = new Messages();
            ArrayList<Task> userData = new ArrayList<>();
            message.greeting();
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String restOfLine;
            CommandType command;

            while (!userInput.equals("bye")) {
                try {
                    String firstWord = userInput.split(" ")[0];

                    try {
                        command = CommandType.valueOf(firstWord);
                    } catch (IllegalArgumentException e) {
                        command = CommandType.unknown;
                    }

                    switch (command) {
                        case list -> {
                            printList(message, userData);
                            userInput = scanner.nextLine();
                        }
                        case mark, unmark-> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            Task.validateTaskNumberMark(taskNumber, userData.size(), userData, command, firstWord);
                            userInput = scanner.nextLine();
                        }
                        case todo -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            ToDo t = new ToDo(restOfLine);
                            t.message();
                            addTask(userData, t);
                            userInput = scanner.nextLine();
                        }
                        case deadline -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Deadline d = new Deadline(restOfLine);
                            d.message();
                            addTask(userData, d);
                            userInput = scanner.nextLine();
                        }
                        case event -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Event e = new Event(restOfLine);
                            e.message();
                            addTask(userData, e);
                            userInput = scanner.nextLine();
                        }
                        case delete -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            Task.validateTaskNumberDel(taskNumber, userData.size(), userData);
                            userInput = scanner.nextLine();
                        }
                        case unknown -> {
                            message.unknown();
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

