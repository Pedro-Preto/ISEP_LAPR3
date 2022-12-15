//package lapr.project.model.DomainClasses;
//
//import lapr.project.model.DomainClasses.GeoLocation;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GeoLocationTest {
//
//    @Test
//    void testEquals() {
//        GeoLocation geoLocation1 = new GeoLocation(1, 2.0,2.0,2.3,1);
//        GeoLocation geoLocation2 = new GeoLocation(2, 2.2,2.2,2.4,2);
//
//        assertFalse(geoLocation1.equals(new String("a")), "Should be false.");
//
//        assertFalse(geoLocation1.equals(geoLocation2), "Should be false.");
//
//        geoLocation2=new GeoLocation(1, 2.0,2.0,2.3,1);
//        assertTrue(geoLocation1.equals(geoLocation2), "Should be true");
//    }
//
//    @Test
//    void testHashCode() {
//        GeoLocation geoLocation1 = new GeoLocation(1, 2.0,2.0,2.3,1);
//        int expected = -1478561759;
//
//        assertEquals(expected, geoLocation1.hashCode(),"Should be equal!");
//    }
//
//    @Test
//    void testToString() {
//        GeoLocation geoLocation1 = new GeoLocation(1, 2.0,2.0,2.3,1);
//        String expected = String.format("GeoLocation->Id:%s,Latitude:%s,Longitude:%s,Altitude:%s,AddressId:%s",1,2.0,2.0,2.3,1);;
//        String actual = geoLocation1.toString();
//
//        assertEquals(expected,actual);
//    }
//}