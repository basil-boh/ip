package steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EventTest {
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>(); // Initialize the ArrayList before each test
    }

    @Test
    public void message() {
        String input = "meeting /from 2025-02-13 1600 /to 2025-02-13 1700";
        Event e = new Event(input);
        tasks.add(e);
        TaskManager taskManager = new TaskManager(tasks);

        String expected = "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] meeting " + "(From: Feb 13 2025, 04:00 pm to: Feb 13 2025, 05:00 pm) \n"
                + "     Now you have " + taskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";

        assertEquals(expected, e.toString());
    }

    @Test
    public void getDescription() {
        String input = "driving lesson /from 2025-02-13 1200 /to 2025-02-13 1330";
        Event e = new Event(input);
        String expected = "driving lesson (From: Feb 13 2025, 12:00 pm to: Feb 13 2025, 01:30 pm) ";
        assertEquals(expected, e.getDescription());
    }
}
