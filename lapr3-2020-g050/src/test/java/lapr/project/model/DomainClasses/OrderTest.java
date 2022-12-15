package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.GeoLocation;
import lapr.project.model.DomainClasses.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testEquals() {
        Order order1 = new Order(1, "Open", 1.2, "ClientNIF1");
        Order order2 = new Order(2, "Closed", 1.3, "ClientNIF2");
        assertFalse(order1.equals(new String("a")), "They should not be equal!");
        assertFalse(order1.equals(order2), "They should not be equal!");

        order2 = new Order(1, "Open", 1.2, "ClientNIF1");
        assertTrue(order1.equals(order2), "They should be equal!");

    }
    @Test
    void testHashCode() {
        Order order1 = new Order(1, "Open", 1.2, "ClientNIF1");
        int expected = -788810277;

        assertEquals(expected, order1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Order order1 = new Order(1, "Open", 1.2, "ClientNIF1");
        String expected = String.format("Order->Id:%s,Status:%s,ItemsPrice:%s,ClientNif:%s",1, "Open", 1.2, "ClientNIF1");
        assertEquals(expected, order1.toString());
    }

}