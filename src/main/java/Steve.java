import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Steve{ //Merge commit main
    public static void main(String[] args) {
        // Initialising objects & variables
            Messages message = new Messages();
            ArrayList<Task> userData = loadTasks();
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
                            addTask(userData, e); //Adds tasks to Task ArrayList
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

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/data/steve.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line); // Parse the line into a Task object
                if (task != null) {
                    tasks.add(task); // Add to the tasks list
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }



    private static Task parseTask(String line) {
        String firstWord = line.split(":")[0];
        String restOfLine = line.substring(firstWord.length() + 1).trim();
        switch (firstWord) {
            case "Todo":
                return new ToDo(restOfLine);
            case "Deadline":
                return new Deadline(restOfLine);
            case "Event":
                return new Event(restOfLine);
            default:
                System.err.println("Unknown task type: " + firstWord);
                return null;
        }
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
            Task.saveTasks(task);
        }
    }
}

