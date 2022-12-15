//package lapr.project.model.DomainClasses;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class VehicleTest {
//    @Test
//    void testEquals() {
//        Vehicle i1 = new Vehicle(1,1.0,1.0,1.0,1.0,1.0,1.0,1.0,"PharNIF1");
//        Vehicle i2 = new Vehicle(2,2.0,2.0,2.0,2.0,2.0,2.0,2.0,"PharNIF2");
//        assertFalse(i1.equals(new String("a")), "Should be false.");
//
//        assertFalse(i1.equals(i2), "Should be false.");
//
//        i2 =new Vehicle(1,1.0,1.0,1.0,1.0,1.0,1.0,1.0,"PharNIF1");
//        assertTrue(i1.equals(i2), "Should be true");
//    }
//
//    @Test
//    void testHashCode() {
//        Vehicle i1 = new Vehicle(1,1.0,1.0,1.0,1.0,1.0,1.0,1.0,"PharNIF1");
//        int expected = -529198193;
//
//        assertEquals(expected, i1.hashCode(), "Should be equal!");
//    }
//
//    @Test
//    void testToString() {
//        Vehicle i1 = new Vehicle(1,1.0,1.0,1.0,1.0,1.0,1.0,1.0,"PharNIF1");
//        String expected = String.format("Vehicle->Id:%s,BatterySize:%s,BatteryConsumption:%s,BatteryEfficiency:%s,CurrentBattery:%s,Weight:%s,FrontalArea:%s,MotorPower:%s,PharmacyNif:%s",1,1.0,1.0,1.0,1.0,1.0,1.0,1.0,"PharNIF1");
//        String actual = i1.toString();
//
//        assertEquals(expected, actual);
//    }
//
//}