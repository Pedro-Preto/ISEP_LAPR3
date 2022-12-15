package lapr.project.model.autorization.model;

import lapr.project.model.DomainClasses.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testHashCode() {
        int expected = 3232;
        User user = new User("a", "a@","b");
        int actual = user.hashCode();

        assertEquals(expected,actual,"Should be true.");

    }

    @Test
    void testEquals() {
        User user1 = new User("Nuno Soares", "nuno.s@soares.nuno","nunosoares");
        User user2 = new User("a", "a@","b");

        assertFalse(user1.equals(user2), "Should be false");

        assertTrue(user1.equals(user1),"Should be true");

        assertFalse(user1.equals(new Item(1,"n1",2.1,1.2)));
    }

    @Test
    void testToString() {
        String expected = "Nuno Soares - nuno.s@soares.nuno";
        User user = new User("Nuno Soares", "nuno.s@soares.nuno","nunosoares");
        String actual = user.toString();

        assertEquals(expected,actual,"Should be true!");
    }
}