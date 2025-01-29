package steve.ui;

import steve.tasks.Task;
import steve.tasks.TaskManager;
import steve.ui.UserInterface;
import steve.storage.FileHandler;
import java.util.ArrayList;

public class Steve{ //Merge commit main msg
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("src/main/data/steve.txt");
        ArrayList<Task> tasks = fileHandler.loadTasks(); // Load tasks from file
        TaskManager taskManager = new TaskManager(tasks); // Initialize TaskManager with loaded tasks
        UserInterface ui = new UserInterface(taskManager); // Initialize UserInterface with TaskManager

        // Start the application
        ui.start();
    }
}

