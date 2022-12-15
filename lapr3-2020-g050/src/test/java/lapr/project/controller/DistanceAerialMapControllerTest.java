package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.AerialPathRegister;
import lapr.project.model.Registers.GeoLocationRegister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistanceAerialMapControllerTest {
    DistanceAerialMapController mapController;
    Platform platform;
    AerialPathRegister aerialPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;


    @BeforeEach
    void setUp() {
        mapController = new DistanceAerialMapController();
        platform = ApplicationEM.getInstance().getPlatform();

        platform.resetData();

        aerialPathRegister = platform.getAerialPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(44440,"Cais da Ribeira", "44440", "Porto", "Portugal");
        addressRegister.addAddress(44441,"Trindade", "44441", "Porto", "Portugal");
        addressRegister.addAddress(44442,"Castelo do Queijo", "44442", "Porto", "Portugal");
        addressRegister.addAddress(44443,"Clérigos", "44443", "Porto", "Portugal");
        addressRegister.addAddress(44444,"Majestic", "44444", "Porto", "Portugal");
        addressRegister.addAddress(44445,"Bolhão", "44445", "Porto", "Portugal");
        addressRegister.addAddress(44446,"Sé", "44446", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(44440,41.14063, -8.61118, 25,44440);
        geoLocationRegister.addGeoLocation(44441,41.15227, -8.60929, 104,44441);
        geoLocationRegister.addGeoLocation(44442,41.16875, -8.68995, 4,44442);
        geoLocationRegister.addGeoLocation(44443,41.14582, -8.61398, 87,44443);
        geoLocationRegister.addGeoLocation(44444,41.14723, -8.60657, 91,44444);
        geoLocationRegister.addGeoLocation(44445,41.14871, -8.60746, 87 ,44445);
        geoLocationRegister.addGeoLocation(44446,41.14331, -8.60914, 82,44446);

        aerialPathRegister.addAerialPath(44443, 44444, 640.0, 45.0, 10.0,
                1.324, Constants.AIR_DRAG_COEFFICIENT); //Clerigos - Majestic

        aerialPathRegister.addAerialPath(44443, 44445, 634.0, 35.0, 6.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Clerigos - Bolhão

        aerialPathRegister.addAerialPath(44445, 44440, 951.0, 35.0, 4.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Bolhão - Cais da ribeira

        aerialPathRegister.addAerialPath(44444, 44440, 829.0, 15.0, 14.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Majestic - Cais da ribeira

        aerialPathRegister.addAerialPath(44440, 44441, 640.0, 45.0, 10.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Cais da ribeira - Trindade

        aerialPathRegister.addAerialPath(44441, 44440, 634.0, 35.0, 6.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Trindade - Cais da ribeira

        aerialPathRegister.addAerialPath(44441, 44443, 640.0, 45.0, 10.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Trindade - Clérigos

        aerialPathRegister.addAerialPath(44443, 44441, 634.0, 35.0, 6.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Clérigos - Trindade

        aerialPathRegister.addAerialPath(44445, 44443, 634.0, 35.0, 6.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Bolhão - Clérigos

        aerialPathRegister.addAerialPath(44440, 44445, 640.0, 45.0, 10.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Cais da ribeira - Bolhão

        aerialPathRegister.addAerialPath(44440, 44444, 640.0, 45.0, 10.0,
                1.324,Constants.AIR_DRAG_COEFFICIENT); //Cais da ribeira - Majestic
    }

    @AfterEach
    void tearDown() {

        platform.resetData();

        System.out.println(mapController.mapToString());

        aerialPathRegister.removeAerialPath(44443,44444);
        aerialPathRegister.removeAerialPath(44443,44445);

        aerialPathRegister.removeAerialPath(44445,44440);
        aerialPathRegister.removeAerialPath(44444,44440);

        aerialPathRegister.removeAerialPath(44440,44441);
        aerialPathRegister.removeAerialPath(44441,44440);

        aerialPathRegister.removeAerialPath(44441,44443);
        aerialPathRegister.removeAerialPath(44443,44441);

        aerialPathRegister.removeAerialPath(44445,44443);

        aerialPathRegister.removeAerialPath(44440,44445);
        aerialPathRegister.removeAerialPath(44440,44444);

        geoLocationRegister.removeGeoLocation(44440);
        geoLocationRegister.removeGeoLocation(44441);
        geoLocationRegister.removeGeoLocation(44442);
        geoLocationRegister.removeGeoLocation(44443);
        geoLocationRegister.removeGeoLocation(44444);
        geoLocationRegister.removeGeoLocation(44445);
        geoLocationRegister.removeGeoLocation(44446);

        addressRegister.removeAddress(44440);
        addressRegister.removeAddress(44441);
        addressRegister.removeAddress(44442);
        addressRegister.removeAddress(44443);
        addressRegister.removeAddress(44444);
        addressRegister.removeAddress(44445);
        addressRegister.removeAddress(44446);
    }

    @Test
    void addConnection() {
        mapController.addAddress(addressRegister.getAddressByID(44440));
        mapController.addAddress(addressRegister.getAddressByID(44441));
        mapController.addAddress(addressRegister.getAddressByID(44442));
        mapController.addAddress(addressRegister.getAddressByID(44443));
        mapController.addAddress(addressRegister.getAddressByID(44444));
        mapController.addAddress(addressRegister.getAddressByID(44445));
        mapController.addAddress(addressRegister.getAddressByID(44446));

        AerialPath path = aerialPathRegister.getPathByID(44443,44444);
        boolean actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(44443,44445);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(44444,44440);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(44445,44440);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        actual = mapController.addConnection(path);
        assertFalse(actual, "Should be false, because the connection already exists.");

        path = new AerialPath(-1,-1,1.0,1.0, 2.0, 1.0, Constants.AIR_DRAG_COEFFICIENT);

        boolean thrown = false;
        try {
            mapController.addConnection(path);
        } catch (IllegalArgumentException ex) {
            thrown = true;
        }
        assertTrue(thrown, "Should be thrown the exception, because the adddress id's are invalid.");

        actual = mapController.addConnection(null);
        assertFalse(actual, "Should be false, because the path is null.");
    }

    @Test
    void getShortestRoute() {
        mapController.addAddress(addressRegister.getAddressByID(44440));
        mapController.addAddress(addressRegister.getAddressByID(44441));
        mapController.addAddress(addressRegister.getAddressByID(44442));
        mapController.addAddress(addressRegister.getAddressByID(44443));
        mapController.addAddress(addressRegister.getAddressByID(44444));
        mapController.addAddress(addressRegister.getAddressByID(44445));
        mapController.addAddress(addressRegister.getAddressByID(44446));

        AerialPath path = aerialPathRegister.getPathByID(44441,44443); //TRINDADE - CLERIGOS
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44443,44441); //CLERIGOS - TRINDADE
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44443,44445); //CLERIGOS - BOLHAO
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44445,44443); //BOLHAO - CLERIGOS
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44443,44444); //CLERIGOS - MAJESTIC
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44445,44440); //BOLHAO - CAIS DA RIBEIRA
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44440,44445); //CAIS DA RIBEIRA - BOLHAO
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44440,44444); //CAIS DA RIBEIRA - MAJESTIC
        mapController.addConnection(path);
        path = aerialPathRegister.getPathByID(44444,44440); //MAJESTIC- CAIS DA RIBEIRA
        mapController.addConnection(path);

        LinkedList<Address> shortestPath = new LinkedList<>();
        double expectedDistance = 2286.859;
        double actualDistance = mapController.getShortestRoute(addressRegister.getAddressByID(44441), addressRegister.getAddressByID(44440),shortestPath);
        System.out.println("Trindade -> Cais da Ribeira" + "\n" + shortestPath);
        List<Address> expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(44441));  //TRINDADE
        expectedPath.add(addressRegister.getAddressByID(44443));  //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(44444));  //MAJESTIC
        expectedPath.add(addressRegister.getAddressByID(44440));  //CAIS DA RIBEIRA

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath,shortestPath,"The paths should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.001, "The energy consumption should be 2402.088m !");

        shortestPath = new LinkedList<>();
        expectedDistance = 2402.088;
        actualDistance = mapController.getShortestRoute(addressRegister.getAddressByID(44440), addressRegister.getAddressByID(44441),shortestPath);
        System.out.println("Cais da Ribeira -> Trindade" + "\n" + shortestPath);
        expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(44440)); //CAIS DA RIBEIRA
        expectedPath.add(addressRegister.getAddressByID(44445)); //BOLHAO
        expectedPath.add(addressRegister.getAddressByID(44443)); //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(44441)); //TRINDADE

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath, shortestPath, "The path should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.001, "The energy consumption should be !");
    }
}