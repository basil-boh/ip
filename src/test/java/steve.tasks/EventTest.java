package steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {

    @Test
    public void message() {
        String input = "meeting /from 2025-02-13 1600 /to 2025-02-13 1700";
        Event e = new Event(input);
        String expected = "____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [E][ ] meeting " + "(From: Feb 13 2025, 04:00 pm to: Feb 13 2025, 05:00 pm) \n"
                + "     Now you have " + e.taskCount() + " tasks in the list.\n"
                + "____________________________________________________________";

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
