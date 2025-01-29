package steve.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testValidTodoMessage() {
        ToDo todo = new ToDo("Read a book");
        String expectedMessage = "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] Read a book\n" +
                "     Now you have " + todo.taskCount() + " tasks in the list.\n" +
                "____________________________________________________________";

        assertEquals(expectedMessage, todo.toString());
    }

    @Test
    public void testInvalidTodoMessage() {
        ToDo todo = new ToDo(""); // Empty description
        String expectedMessage = "Description cannot be empty. Usage: todo <description>";

        assertEquals(expectedMessage, todo.getDescription());
    }
}
