//package lapr.project.model.DomainClasses;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PathTest {
//    @Test
//    void testEquals() {
//        Path i1 = new Path(1, 2,1.1,1.1,1.1);
//        Path i2 = new Path(2, 3,1.1,1.2,1.2);
//        assertFalse(i1.equals(new String("a")), "Should be false.");
//
//        assertFalse(i1.equals(i2), "Should be false.");
//
//        i2 =new Path(1, 2,1.1,1.1,1.1);
//        assertTrue(i1.equals(i2), "Should be true");
//    }
//
//    @Test
//    void testHashCode() {
//        Path i1 = new Path(1, 2,1.1,1.1,1.1);
//        int expected = 2036891650;
//
//        assertEquals(expected, i1.hashCode(), "Should be equal!");
//    }
//
//    @Test
//    void testToString() {
//        Path i1 = new Path(1, 2,1.1,1.1,1.1);
//        String expected = String.format("Path->GeoLocationStart:%s,GeoLocationEnd:%s,Distance:%s,WindDirection:%s,WindVelocity:%s", 1, 2,1.1,1.1,1.1);
//        String actual = i1.toString();
//
//        assertEquals(expected, actual);
//    }
//
//}