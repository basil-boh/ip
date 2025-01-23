import java.util.ArrayList;
import java.util.Scanner;

public class Steve{
    public static void main(String[] args) {
            Messages message = new Messages();
            ArrayList<Task> userData = new ArrayList<>();
            System.out.println(message.greeting());
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String restOfLine = "";

            while (!userInput.equals("bye")) {
                try {
                    String firstWord = userInput.split(" ")[0];

                    switch (firstWord) {
                        case "list" -> {
                            System.out.println("____________________________________________________________");
                            for (int i = 0, j = 0; i < userData.size(); i++) {
                                Task currTask = userData.get(i);
                                System.out.println(++j + currTask.list());

                            }
                            System.out.println("____________________________________________________________");
                            userInput = scanner.nextLine();
                        }
                        case "mark" -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            if (taskNumber < 1 || taskNumber > userData.size()) {
                                throw new InvalidTaskNumberException("Task number " + taskNumber + " is invalid. Please enter a number within Task Count Range: " + userData.size());
                            }
                            Task task = userData.get(taskNumber - 1);
                            task.markAsDone();
                            System.out.println(task.message(firstWord));
                            userInput = scanner.nextLine();
                        }
                        case "unmark" -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            if (taskNumber < 1 || taskNumber > userData.size()) {
                                throw new InvalidTaskNumberException("Task number " + taskNumber + " is invalid. Please enter a number within Task Count Range: " + userData.size());
                            }
                            Task task = userData.get(taskNumber - 1); // Adjust for 0-based index
                            task.markAsUndone();
                            System.out.println(task.message(firstWord));
                            userInput = scanner.nextLine();
                        }
                        case "todo" -> {
                            Scanner lineScanner = new Scanner(userInput);
                            lineScanner.next();
                            if (lineScanner.hasNextLine()) {
                                restOfLine = lineScanner.nextLine().trim();
                            }
                            if (restOfLine.isEmpty()) {
                                System.out.println(message.descriptionEmpty());
                                userInput = scanner.nextLine();
                                continue;
                            }
                            ToDo t = new ToDo(restOfLine);
                            System.out.println(t.message());
                            userData.add(t);
                            userInput = scanner.nextLine();
                        }
                        case "deadline" -> {
                            Scanner lineScanner = new Scanner(userInput);
                            lineScanner.next();
                            if (lineScanner.hasNextLine()) {
                                restOfLine = lineScanner.nextLine().trim();
                            }
                            if (restOfLine.isEmpty()) {
                                System.out.println(message.descriptionEmpty());
                                userInput = scanner.nextLine();
                                continue;
                            }
                            Deadline d = new Deadline(restOfLine);
                            System.out.println(d.message());
                            userData.add(d);
                            userInput = scanner.nextLine();
                        }
                        case "event" -> {
                            Scanner lineScanner = new Scanner(userInput);
                            lineScanner.next();
                            if (lineScanner.hasNextLine()) {
                                restOfLine = lineScanner.nextLine().trim(); // Read the rest of the line
                            }
                            if (restOfLine.isEmpty()) {
                                System.out.println(message.descriptionEmpty());
                                userInput = scanner.nextLine();
                                continue;
                            }
                            Event e = new Event(restOfLine);
                            System.out.println(e.message());
                            userData.add(e);
                            userInput = scanner.nextLine();
                        }
                        case "delete" -> {
                            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                            if (taskNumber < 1 || taskNumber > userData.size()) {
                                throw new InvalidTaskNumberException("Task number " + taskNumber + " is invalid. Please enter a number within Task Count Range: " + userData.size());
                            }
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
