package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockItemsTest {
    @Test
    void testEquals() {
        StockItems i1 = new StockItems(1, 2,1);
        StockItems i2 = new StockItems(2, 1,2);
        assertFalse(i1.equals(new String("a")), "Should be false.");

        assertFalse(i1.equals(i2), "Should be false.");

        i2 =new StockItems(1, 2,1);
        assertTrue(i1.equals(i2), "Should be true");
    }

    @Test
    void testHashCode() {
        StockItems i1 = new StockItems(1, 2,1);
        int expected = 30815;

        assertEquals(expected, i1.hashCode(), "Should be equal!");
    }

    @Test
    void testToString() {
        StockItems i1 = new StockItems(1, 2,1);
        String expected = String.format("StockItems->ItemId:%s,StockId:%s,ItemsQuantity:%s",1, 2,1);
        String actual = i1.toString();

        assertEquals(expected, actual);
    }

}
