package lapr.project.model.DomainClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandPathTest {


    @Test
    void testEquals() {

        LandPath a1 = new LandPath(1,1,1.1,1.2,1.3,1.4,1.5);
        LandPath a2 = new LandPath(2,3,2.1,2.2,2.3,2.4,2.5);
        assertFalse(a1.equals(new String("a")),"Must be false");
        assertFalse(a1.equals(a2), "Must be false");
        a2=new LandPath(1,1,1.1,1.2,1.3,1.4,1.5);
        assertTrue(a1.equals(a2), "Must be True");
    }

    @Test
    void testHashCode() {
        LandPath a1 = new LandPath(1,1,1.1,1.2,1.3,1.4,1.5);
        int expected = 220698434;
        assertEquals(expected, a1.hashCode(), "Should be equal");
    }

    @Test
    void testToString() {
        Path p1 = new Path(1,1,1.1,1.2,1.3) {
            @Override
            public int getGeoLocationStart() {
                return super.getGeoLocationStart();
            }

            @Override
            public int getGeoLocationEnd() {
                return super.getGeoLocationEnd();
            }

            @Override
            public Double getDistance() {
                return super.getDistance();
            }

            @Override
            public Double getWindDirection() {
                return super.getWindDirection();
            }

            @Override
            public Double getWindVelocity() {
                return super.getWindVelocity();
            }
        };

        LandPath a1 = new LandPath(1,1,1.1,1.2,1.3,1.4,1.5);
        String actual = a1.toString();
        String expected =String.format("%s\nRoadResistanceCoefficient: %s \nInclination:%s",p1.toString(),1.4,1.5);
        assertEquals(expected, actual);

    }

}