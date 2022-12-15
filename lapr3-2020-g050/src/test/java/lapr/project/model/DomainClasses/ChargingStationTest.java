package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingStationTest {

    @Test
    void testEquals() {
        ChargingStation cs1 = new ChargingStation(1, 1.0, 1.1);
        ChargingStation cs2 = new ChargingStation(2, 1.1, 1.2);
        assertFalse(cs1.equals(new String("a")), "Must be false");
        assertFalse(cs1.equals(cs2), "Must be false");
        cs2=new ChargingStation(1, 1.0, 1.1);
        assertTrue(cs1.equals(cs2), "Must be True");
    }

    @Test
    void testHashCode() {
        ChargingStation cs1 = new ChargingStation(1, 1.0, 1.1);
        int expected = 1685616675;
        assertEquals(expected,cs1.hashCode(),"Should be equal");
    }

    @Test
    void testToString() {
        ChargingStation cs1 = new ChargingStation(1, 1.0, 1.1);
        String actual = cs1.toString();
        String expected =String.format("ChargingStation->ParkSlotId:%s,Power:%s,Capacitys:%s",1, 1.0, 1.1);
        assertEquals(expected, actual);

    }
}