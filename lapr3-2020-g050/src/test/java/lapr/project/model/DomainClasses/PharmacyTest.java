package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.GeoLocation;
import lapr.project.model.DomainClasses.Pharmacy;
import lapr.project.model.DomainClasses.PharmacyAdministrator;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTest {

    @Test
    void testEquals() {
        Pharmacy pharmacy1 = new Pharmacy("NIF1","Farmácia da esquina",1);
        Pharmacy  pharmacy2= new Pharmacy("NIF2","Farmácia da retunda",2);
        assertFalse(pharmacy1.equals(new String("a")), "Should be false.");

        assertFalse(pharmacy1.equals(pharmacy2), "Should be false.");

        pharmacy2 = new Pharmacy("NIF1","Farmácia da esquina",1);
        assertTrue(pharmacy1.equals(pharmacy2), "Should be true");
    }

    @Test
    void testHashCode() {
        Pharmacy pharmacy1 = new Pharmacy("NIF1","Farmácia da esquina",1);
        int expected = 1906975279;

        assertEquals(expected, pharmacy1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Pharmacy pharmacy1 = new Pharmacy("NIF1","Farmácia da esquina",1);
        String expected =  String.format("Phamacy->NIF-%S,Designation:%s,AddressId:%s","NIF1","Farmácia da esquina",1);
        String actual = pharmacy1.toString();

        assertEquals(expected,actual);
    }

}