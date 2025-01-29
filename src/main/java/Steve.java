import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Steve{ //Merge commit main
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("src/main/data/steve.txt");
        ArrayList<Task> tasks = fileHandler.loadTasks(); // Load tasks from file
        TaskManager taskManager = new TaskManager(tasks); // Initialize TaskManager with loaded tasks
        UserInterface ui = new UserInterface(taskManager); // Initialize UserInterface with TaskManager

        // Start the application
        ui.start();
    }
}

