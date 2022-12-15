package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsOfOrderTest {

    @Test
    void testEquals() {
        ItemsOfOrder i1 = new ItemsOfOrder(1, 1, 1);
        ItemsOfOrder i2 = new ItemsOfOrder(2, 2, 2);

        assertFalse(i1.equals(new String("a")), "Should be false.");

        assertFalse(i1.equals(i2), "Should be false.");

        i2 = new ItemsOfOrder(1, 1, 1);
        assertTrue(i1.equals(i2), "Should be true");
    }

    @Test
    void testHashCode() {
        ItemsOfOrder i1 = new ItemsOfOrder(1, 1, 1);
        int expected = 30784;

        assertEquals(expected, i1.hashCode(), "Should be equal!");
    }

    @Test
    void testToString() {
        ItemsOfOrder i1 = new ItemsOfOrder(1, 1, 1);
        String expected = String.format("ItemsOfOrder->ItemId:%s,OrderId:%s,ItemsQuantity:%s",1, 1, 1);
        String actual = i1.toString();

        assertEquals(expected, actual);
    }
}