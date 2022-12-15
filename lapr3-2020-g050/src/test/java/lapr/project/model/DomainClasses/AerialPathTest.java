package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AerialPathTest {


    @Test
    void testEquals() {

        AerialPath a1 = new AerialPath(1,1,1.1,1.2,1.3,1.4,1.5);
        AerialPath a2 = new AerialPath(2,3,2.1,2.2,2.3,2.4,2.5);
        assertFalse(a1.equals(new String("a")),"Must be false");
        assertFalse(a1.equals(a2), "Must be false");
        a2=new AerialPath(1,1,1.1,1.2,1.3,1.4,1.5);
        assertTrue(a1.equals(a2), "Must be True");
    }

    @Test
    void testHashCode() {
        AerialPath a1 = new AerialPath(1,1,1.1,1.2,1.3,1.4,1.5);
        int expected = 220698434;
        assertEquals(expected, a1.hashCode(), "Should be equal");
    }

    @Test
    void testToString() {
        AerialPath a1 = new AerialPath(1,1,1.1,1.2,1.3,1.4,1.5);
        String actual = a1.toString();
        String expected =String.format("AerialPath->airDensity%s,airDragCoefficient%s",1.4,1.5);
        assertEquals(expected, actual);
    }

}