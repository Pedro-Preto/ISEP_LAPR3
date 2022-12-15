package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Credits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditsTest {

    @Test
    void testEquals() {
        Credits credits1 = new Credits(1, 1, "NIF1");
        Credits credits2 = new Credits(2, 2, "NIF2");
        credits2.setNumberOfCredits(2);

        assertFalse(credits1.equals(new String("a")), "Should be false.");

        assertFalse(credits1.equals(credits2), "Should be false.");

        credits2 = new Credits(1, 1, "NIF1");
        assertTrue(credits1.equals(credits2), "Should be true");
    }

    @Test
    void testHashCode() {
        Credits credits1 = new Credits(1, 1, "NIF1");
        int expected = 32;

        assertEquals(expected, credits1.hashCode(), "Should be equal!");
    }

    @Test
    void testToString() {
        Credits credits1 = new Credits(1, 1, "NIF1");
        String expected = String.format("Credits->Id:%s, ratioEuroToCredit= '%s', numberOfCredits= '%s'%n",1,1.0, 1, "NIF1");
        String actual = credits1.toString();

        assertEquals(expected, actual);
    }
}
