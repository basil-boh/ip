package steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>(); // Initialize the ArrayList before each test
    }

    @Test
    public void testValidTodoMessage() {
        ToDo todo = new ToDo("Read a book");
        tasks.add(todo);
        TaskManager taskManager = new TaskManager(tasks);

        String expectedMessage = "______________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] Read a book\n"
                + "     Now you have " + taskManager.getTaskSize() + " tasks in the list.\n"
                + "______________________________\n";

        assertEquals(expectedMessage, todo.toString());
    }

    @Test
    public void testInvalidTodoMessage() {
        ToDo todo = new ToDo(""); // Empty description
        String expectedMessage = "Description cannot be empty. Usage: todo <description>";

        assertEquals(expectedMessage, todo.getDescription());
    }
}

