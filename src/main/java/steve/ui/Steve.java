package steve.ui;

import java.util.ArrayList;

import steve.storage.FileHandler;
import steve.tasks.Task;
import steve.tasks.TaskManager;

/**
 * Main entry point for the Steve chatbot application.
 * Initializes the system by loading tasks, creating necessary objects, and starting the user interface.
 */
public class Steve {

    /**
     * The main method that runs the application.
     * Loads tasks from the file, initializes the TaskManager and UserInterface, and starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("src/main/data/steve.txt");
        ArrayList<Task> tasks = fileHandler.loadTasks(); // Load tasks from file
        TaskManager taskManager = new TaskManager(tasks); // Initialize TaskManager with loaded tasks
        UserInterface ui = new UserInterface(taskManager); // Initialize UserInterface with TaskManager

        // Start the application
        ui.start();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Steve heard: " + input;
    }
}


