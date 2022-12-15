package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void testEquals() {
        Delivery dev1 = new Delivery(1, "Open",2.0,"NIF1",1);
        Delivery dev2 =new Delivery(2, "Closed",3.0,"NIF2",2);

        assertFalse(dev1.equals(new String("a")), "Should be false.");

        assertFalse(dev1.equals(dev2), "Should be false.");

        dev2=new Delivery(1, "Open",2.0,"NIF1",1);
        assertTrue(dev1.equals(dev2), "Should be true");
    }

    @Test
    void testHashCode() {
        Delivery dev1 = new Delivery(1, "Open",2.0,"NIF1",1);
        int expected = 1578936977;

        assertEquals(expected, dev1.hashCode(),"Should be equal!");
    }

    @Test
    void testToString() {
        Delivery dev1 = new Delivery(1, "Open",2.0,"NIF1",1);
        String expected = String.format("Delivery-> orderId:%s,status:%s,shippingPrice:%s,courierNIF:%s,DroneId:%s",1, "Open",2.0,"NIF1",1);
        String actual = dev1.toString();

        assertEquals(expected,actual);
    }
}