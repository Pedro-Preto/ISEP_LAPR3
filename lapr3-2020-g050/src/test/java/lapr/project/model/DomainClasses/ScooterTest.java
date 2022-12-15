package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Scooter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest {



    @Test
    void testEquals() {
        Scooter scooter1 = new Scooter(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1","QR1");
        Scooter scooter2 = new Scooter(2,1.4,3.4,0.4,0.5, 0.3,1.3,1.3,"PharNIF2","QR2");

        assertFalse(scooter1.equals(new String("a")),"Should be false.");

        assertFalse(scooter1.equals(scooter2),"Should be false.");

        scooter2=new Scooter(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1","QR1");
        assertTrue(scooter1.equals(scooter2), "Should be true.");

    }

    @Test
    void testHashCode() {
        Scooter scooter1 = new Scooter(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1","QR1");
        int expected = 80463;

        assertEquals(expected, scooter1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Vehicle v1= new Vehicle(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1") {
            @Override
            public int getId() {
                return super.getId();
            }

            @Override
            public double getBatterySize() {
                return super.getBatterySize();
            }

            @Override
            public double getBatteryConsumption() {
                return super.getBatteryConsumption();
            }

            @Override
            public double getBatteryEfficiency() {
                return super.getBatteryEfficiency();
            }

            @Override
            public double getCurrentBattery() {
                return super.getCurrentBattery();
            }

            @Override
            public double getWeight() {
                return super.getWeight();
            }

            @Override
            public double getFrontalArea() {
                return super.getFrontalArea();
            }

            @Override
            public double getMotorPower() {
                return super.getMotorPower();
            }

            @Override
            public String getPharmacyNif() {
                return super.getPharmacyNif();
            }
        };
        Scooter scooter1 = new Scooter(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1","QR1");
        String expected = String.format("Scooter battery size: %f kWh \n Scooter battery efficiency: %f \nScooter mass: %f kg\nScooter frontal area: %f m^2\n",v1.getBatterySize(),v1.getBatteryEfficiency(),v1.getWeight(),v1.getFrontalArea());

        assertEquals(expected, scooter1.toString());
    }

    @Test
    void compareTo() {
        Scooter scooter1 = new Scooter(1,1.3,3.1,0.1,0.4, 0.6,1.2,1.2,"PharNIF1","QR1");
        Scooter scooter2 = new Scooter(2,1.4,3.4,0.4,0.5, 0.3,1.3,1.3,"PharNIF2","QR2");

        assertTrue(scooter2.compareTo(scooter1) > 0, "The scooter with id 2, should be bigger.");

        assertTrue(scooter1.compareTo(scooter2) < 0, "The scooter with id 1, should be smaller.");

        assertTrue(scooter1.compareTo(scooter1) == 0, "Should be the same.");
    }
}