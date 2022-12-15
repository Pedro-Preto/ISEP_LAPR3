//package lapr.project.model.DomainClasses;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ParkTest {
//
//    @Test
//    void testEquals() {
//        Park i1 = new Park(1, "PharNIF1");
//        Park i2 = new Park(2, "PharNIF2");
//        assertFalse(i1.equals(new String("a")), "Should be false.");
//
//        assertFalse(i1.equals(i2), "Should be false.");
//
//        i2 = new Park(1, "PharNIF1");
//        assertTrue(i1.equals(i2), "Should be true");
//    }
//
//    @Test
//    void testHashCode() {
//        Park i1 = new Park(1, "PharNIF1");
//        int expected = -1613285553;
//
//        assertEquals(expected, i1.hashCode(), "Should be equal!");
//    }
//
//    @Test
//    void testToString() {
//        Park i1 = new Park(1, "PharNIF1");
//        String expected = String.format("Park->Id:%s,PharmacyNIF:%s", 1, "PharNIF1");
//        String actual = i1.toString();
//
//        assertEquals(expected, actual);
//    }
//
//}