package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Stock;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void testEquals() {
        Stock s1 = new Stock(1, "PharNIF1");
        Stock s2 = new Stock(2, "PharNIF2");

        assertFalse(s1.equals(new String("a")), "Should be false.");

        assertFalse(s1.equals(s2), "Should be false.");

        s2=new Stock(1, "PharNIF1");
        assertTrue(s1.equals(s2), "Should be true");
    }

    @Test
    void testHashCode() {
        Stock s1 = new Stock(1, "PharNIF1");
        int expected = -1613285553;

        assertEquals(expected, s1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Stock s1 = new Stock(1, "PharNIF1");
        String expected = String.format("Stock->Id:%s,PharmacyNif:%s", 1, "PharNIF1");

        String actual = s1.toString();

        assertEquals(expected, actual);
    }


}