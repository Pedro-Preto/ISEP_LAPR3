package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {


    @Test
    void testToString() {
        Drone drone1 = new Drone(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1",1.0);
        String expected = String.format("Drone->Payload:%s",1.0);

        assertEquals(expected, drone1.toString());
    }

    @Test
    void compareTo() {
        Drone drone1 = new Drone(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1",1.0);
        Drone drone2 = new Drone(2,1.2,3.2,0.2,0.2, 0.2,1.1,1.1,"PharNIF2",1.2);

        assertTrue(drone2.compareTo(drone1) > 0, "The scooter with id 2, should be bigger.");

        assertTrue(drone1.compareTo(drone2) < 0, "The scooter with id 1, should be smaller.");

        assertTrue(drone1.compareTo(drone1) == 0, "Should be the same.");
    }
}