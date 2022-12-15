package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {


    @Test
    void testEquals() {
        Receipt s1 = new Receipt(1, 1.2,1,"NIF1","PharNIF1");
        Receipt s2 = new Receipt(2, 1.3,2,"NIF2","PharNIF2");

        assertFalse(s1.equals(new String("a")), "Should be false.");

        assertFalse(s1.equals(s2), "Should be false.");

        s2=new Receipt(1, 1.2,1,"NIF1","PharNIF1");
        assertTrue(s1.equals(s2), "Should be true");
    }

    @Test
    void testHashCode() {
        Receipt s1 = new Receipt(1, 1.2,1,"NIF1","PharNIF1");
        int expected = 1632078954;

        assertEquals(expected, s1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Receipt s1 = new Receipt(1, 1.2,1,"NIF1","PharNIF1");
        String expected = String.format("Receipt->Id:%s,TotalPrice:%s,OrderId:%s,ClientNIF:%s,PharmacyNIF:%s\n",1, 1.2,1,"NIF1","PharNIF1");

        String actual = s1.toString();

        assertEquals(expected, actual);
    }

}