package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.GeoLocation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEquals() {

        Address a1 = new Address(1, "Rua1", "1", "Benfica", "Portugal");
        Address a2 = new Address(1, "Rua2", "2", "Porto", "Portugal");
        assertFalse(a1.equals(new String("a")), "Must be false");
        assertFalse(a1.equals(a2), "Must be false");
        a2=new Address(1, "Rua1", "1", "Benfica", "Portugal");
        assertTrue(a1.equals(a2), "Must be True");
    }

    @Test
    void testHashCode() {
        Address a1 = new Address(1, "Rua1", "1", "Benfica", "Portugal");
        int expected = 1481703984;
        assertEquals(expected, a1.hashCode(), "Should be equal");
    }

    @Test
    void testToString() {
        Address a1 = new Address(1, "Rua1", "1", "Benfica", "Portugal");
        String actual = a1.toString();
        String expected = String.format("Street: %s | DoorNumber:%s","Rua1", "1");
        assertEquals(expected, actual);
    }


}