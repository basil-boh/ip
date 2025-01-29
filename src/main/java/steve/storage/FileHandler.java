package steve.storage;
import steve.tasks.Deadline;
import steve.tasks.Task;
import steve.tasks.ToDo;
import steve.tasks.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
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
}