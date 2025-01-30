package steve.ui;
import steve.commands.Command;
import steve.commands.CommandCreate;
import steve.exceptions.InvalidCommandException;
import steve.tasks.TaskManager;

import java.util.Scanner;

// Manages UI flow of chatbot
public class UserInterface {
    private TaskManager taskManager;
    private Scanner scanner;

    public UserInterface(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Messages.greeting();
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                Command command = CommandCreate.createCommand(userInput, taskManager);
                command.execute();
            } catch (InvalidCommandException e) {
                Messages.unknown();
            } catch (NumberFormatException e) {
                System.out.println(Messages.formatExceptionMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            userInput = scanner.nextLine();
        }
        Messages.goodbye();
    }
}