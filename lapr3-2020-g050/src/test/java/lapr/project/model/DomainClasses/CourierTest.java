package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    @Test
    void testEquals() {

        Courier a1 = new Courier("NIF1", "Rua1", "Mail1", "Phone1", "IBAN1",1.3,1.9,"PharNIF1");
        Courier a2 = new Courier("NIF2", "Rua1", "Mail2", "Phone2", "IBAN2",1.4,1.0,"PharNIF2");
        assertFalse(a1.equals(new String("a")), "Must be false");
        assertFalse(a1.equals(a2), "Must be false");
        a2=new Courier("NIF1", "Rua1", "Mail1", "Phone1", "IBAN1",1.3,1,"NIF1");
        assertTrue(a1.equals(a2), "Must be True");
    }

    @Test
    void testHashCode() {
        Courier a1 = new Courier("NIF1", "Rua1", "Mail1", "Phone1", "IBAN1",1.3,1.9,"PharNIF1");
        int expected = 1484175414;
        assertEquals(expected, a1.hashCode(), "Should be equal");
    }

    @Test
    void testToString() {
        Courier a1 = new Courier("NIF1", "Rua1", "Mail1", "Phone1", "IBAN1",1.3,1.9,"PharNIF1");
        String actual = a1.toString();
        String expected = String.format("Courier->NIF:%s,Name:%s,EmailAddress:%s,CellPhone:%s,IBAN:%s,Weight:%s,Payload:%s,PharmacyNIF:%s","NIF1", "Rua1", "Mail1", "Phone1", "IBAN1",1.3,1.9,"PharNIF1");
        assertEquals(expected, actual);
    }
}