package lapr.project.model.autorization.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    @Test
    void testHashCode() {
        UserRole userRole = new UserRole(21,"aa", "aa");
        int expected = 3265;
        assertEquals(expected, userRole.hashCode(), "Should be equal");
    }

    @Test
    void testEquals() {
        UserRole userRole1 = new UserRole(50,"aa", "aa");
        UserRole userRole2 = new UserRole(51,"ab","ab");

        assertFalse(userRole1.equals(userRole2), "Should be false");

        assertTrue(userRole1.equals(userRole1), "Should be true");

        assertFalse(userRole1.equals(new User("a","a","a")));
    }

    @Test
    void testToString() {
        UserRole userRole = new UserRole(56,"aa", "aa");
        String expected = "aa - aa";

        assertEquals(expected, userRole.toString(), "Should be equals!");
    }
}