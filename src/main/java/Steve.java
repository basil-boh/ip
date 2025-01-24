import java.util.ArrayList;
import java.util.Scanner;

public class Steve{
    public static void main(String[] args) {
            Messages message = new Messages();
            ArrayList<Task> userData = new ArrayList<>();
            System.out.println(message.greeting());
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String restOfLine;

            while (!userInput.equals("bye")) {
                try {
                    String firstWord = userInput.split(" ")[0];

                    switch (firstWord) {
                        case "list" -> {
                            System.out.println(message.border());
                            for (int i = 0, j = 0; i < userData.size(); i++) {
                                Task currTask = userData.get(i);
                                System.out.println(++j + currTask.list());
                            }
                            System.out.println(message.border());
                            userInput = scanner.nextLine();
                        }
                        case "mark", "unmark" -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            Task.validateTaskNumber(taskNumber, userData.size());
                            Task task = userData.get(taskNumber - 1);
                            task.markDoneOrNot(firstWord.equals("mark"));
                            System.out.println(task.message(firstWord));
                            userInput = scanner.nextLine();
                        }
                        case "todo" -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            ToDo t = new ToDo(restOfLine);
                            System.out.println(t.message());
                            if(!restOfLine.isEmpty()) {
                                userData.add(t);
                            }
                            userInput = scanner.nextLine();
                        }
                        case "deadline" -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Deadline d = new Deadline(restOfLine);
                            System.out.println(d.message());
                            if(!restOfLine.isEmpty()) {
                                userData.add(d);
                            }
                            userInput = scanner.nextLine();
                        }
                        case "event" -> {
                            restOfLine = userInput.substring(firstWord.length()).trim();
                            Event e = new Event(restOfLine);
                            System.out.println(e.message());
                            if(!restOfLine.isEmpty()) {
                                userData.add(e);
                            }
                            userInput = scanner.nextLine();
                        }
                        case "delete" -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            Task.validateTaskNumber(taskNumber, userData.size());
                            Task deletedTask = userData.remove(taskNumber - 1);
                            System.out.println(deletedTask.delete());
                            userInput = scanner.nextLine();
                        }
                        default -> {
                            System.out.println(message.unknown());
                            userInput = scanner.nextLine();
                        }
                    }
                }
                catch (InvalidTaskNumberException e) { //E.g. Catches invalid inputs like entering number 0 < or > taskCount
                    System.out.println(e.getMessage());
                    userInput = scanner.nextLine();
                }
                catch (NumberFormatException e) { //E.g. Catches invalid inputs like mark/unmark abc
                    System.out.println("Please enter a valid number for the task!");
                    userInput = scanner.nextLine();
                }
            }
            System.out.println(message.goodbye());
    }
}
