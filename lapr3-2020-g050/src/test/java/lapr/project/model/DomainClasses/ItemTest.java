package lapr.project.model.DomainClasses;

import lapr.project.model.DomainClasses.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {



    @Test
    void testToString() {

        Item a1 = new Item(1, "Cegripe", 5.5,1.0);
        String actual = a1.toString();
        String expected = String.format("Item->id:%s,name:%s,price:%s,Weight:%s %n",1, "Cegripe", 5.5,1.0);
        assertEquals(expected, actual);
    }

    @Test
    void compareTo() {
        Item it1 = new Item(1, "Cegripe", 5.5,1.0);
        Item it2 = new Item(2, "Medicinal-Weed", 5.7,6.0);

        assertTrue(it2.compareTo(it1) > 0, "The item with id 2, should be bigger.");

        assertTrue(it1.compareTo(it2) < 0, "The item with id 1, should be smaller.");

        assertTrue(it1.compareTo(it1) == 0, "Should be the same.");
    }

}