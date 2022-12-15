package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.PharmacyAdministrator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyAdministratorTest {

    @Test
    void testEquals() {
        PharmacyAdministrator pharmacyAdministrator1 = new PharmacyAdministrator("Mail1", "Darwin", "PharNIF1");
        PharmacyAdministrator pharmacyAdministrator2 = new PharmacyAdministrator("Mail2", "Seferovic", "PharNIF2");

        assertFalse(pharmacyAdministrator1.equals(new String("a")), "Should be false!");

        assertFalse(pharmacyAdministrator1.equals(pharmacyAdministrator2), "Should be false!");

        pharmacyAdministrator2 = new PharmacyAdministrator("Mail1", "Darwin", "PharNIF1");
        assertTrue(pharmacyAdministrator1.equals(pharmacyAdministrator2), "Should be true!");

    }

    @Test
    void testHashCode() {
        PharmacyAdministrator pharmacyAdministrator1 = new PharmacyAdministrator("Mail1", "Darwin", "PharNIF1");
        int expected = -1114194540;

        assertEquals(expected, pharmacyAdministrator1.hashCode(), "Shuld be true");
    }

    @Test
    void testToString() {
        PharmacyAdministrator pharmacyAdministrator1 = new PharmacyAdministrator("Mail1", "Darwin", "PharNIF1");
        String expected =  String.format("PharmacyAdministrator->EmailAddress:%s,Name:%s,PharmacyNIF:%s","Mail1", "Darwin", "PharNIF1");
        String actual = pharmacyAdministrator1.toString();

        assertEquals(expected,actual);
    }
}