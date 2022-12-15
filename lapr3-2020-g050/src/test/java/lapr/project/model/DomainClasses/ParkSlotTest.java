package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkSlotTest {

    @Test
    void testEquals() {
        ParkSlot i1 = new ParkSlot(1, 1, 1);
        ParkSlot i2 = new ParkSlot(2, 2, 2);

        assertFalse(i1.equals(new String("a")), "Should be false.");

        assertFalse(i1.equals(i2), "Should be false.");

        i2 = new ParkSlot(1, 1, 1);
        assertTrue(i1.equals(i2), "Should be true");
    }

    @Test
    void testHashCode() {
        ParkSlot i1 = new ParkSlot(1, 1, 1);
        int expected = 30784;

        assertEquals(expected, i1.hashCode(), "Should be equal!");
    }

    @Test
    void testToString() {
        ParkSlot i1 = new ParkSlot(1, 1, 1);
        String expected = String.format("ParkSlot->Id:%s,ParkId:%s,VehicleId:%s",1, 1, 1);
        String actual = i1.toString();

        assertEquals(expected, actual);
    }

}