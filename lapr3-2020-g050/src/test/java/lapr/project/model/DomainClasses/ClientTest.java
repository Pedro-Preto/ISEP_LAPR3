package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    void testEquals() {
        Client client1 = new Client("NIF1","mail1","Grimaldo",1);
        Client client2 = new Client("NIF2","mail2","Odisseias",2);
        assertFalse(client1.equals(new String("a")), "Should be false.");

        assertFalse(client1.equals(client2), "Should be false.");

        client2 = new Client("NIF1","mail1","Grimaldo",1);
        assertTrue(client1.equals(client2), "Should be true");
    }

    @Test
    void testHashCode() {
        Client client1 = new Client("NIF1","mail1","Grimaldo",1);
        int expected = -1552534607;

        assertEquals(expected, client1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Client client1 = new Client("NIF1","mail1","Grimaldo",1);
        String expected =  String.format("Client->NIF:%s,EmailAddress:%s,Name:%s,AddressId:%s","NIF1","mail1","Grimaldo",1);
        String actual = client1.toString();

        assertEquals(expected,actual);
    }

}
