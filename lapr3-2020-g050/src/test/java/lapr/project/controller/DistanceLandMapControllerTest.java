package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.GeoLocationRegister;
import lapr.project.model.Registers.LandPathRegister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistanceLandMapControllerTest {
    DistanceLandMapController mapController;
    Platform platform;
    LandPathRegister landPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;


    @BeforeEach
    void setUp() {
        mapController = new DistanceLandMapController();
        platform = ApplicationEM.getInstance().getPlatform();

        platform.resetData();

        landPathRegister = platform.getLandPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(55550,"Cais da Ribeira", "55550", "Porto", "Portugal");
        addressRegister.addAddress(55551,"Trindade", "55551", "Porto", "Portugal");
        addressRegister.addAddress(55552,"Castelo do Queijo", "55552", "Porto", "Portugal");
        addressRegister.addAddress(55553,"Clérigos", "55553", "Porto", "Portugal");
        addressRegister.addAddress(55554,"Majestic", "55554", "Porto", "Portugal");
        addressRegister.addAddress(55555,"Bolhão", "55555", "Porto", "Portugal");
        addressRegister.addAddress(55556,"Sé", "55556", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(55550,41.14063, -8.61118, 25,55550);
        geoLocationRegister.addGeoLocation(55551,41.15227, -8.60929, 104,55551);
        geoLocationRegister.addGeoLocation(55552,41.16875, -8.68995, 4,55552);
        geoLocationRegister.addGeoLocation(55553,41.14582, -8.61398, 87,55553);
        geoLocationRegister.addGeoLocation(55554,41.14723, -8.60657, 91,55554);
        geoLocationRegister.addGeoLocation(55555,41.14871, -8.60746, 87 ,55555);
        geoLocationRegister.addGeoLocation(55556,41.14331, -8.60914, 82,55556);

        landPathRegister.addLandPath(55553, 55554, 640.0, 45.0, 10.0,
                0.7,0.0); //Clerigos - Majestic

        landPathRegister.addLandPath(55553, 55555, 634.0, 35.0, 6.0,
                0.7,0.0); //Clerigos - Bolhão

        landPathRegister.addLandPath(55555, 55550, 951.0, 35.0, 4.0,
                0.7,0.0); //Bolhão - Cais da ribeira

        landPathRegister.addLandPath(55554, 55550, 829.0, 15.0, 14.0,
                0.7,0.0); //Majestic - Cais da ribeira

        landPathRegister.addLandPath(55550, 55551, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Trindade

        landPathRegister.addLandPath(55551, 55550, 634.0, 35.0, 6.0,
                0.7,0.0); //Trindade - Cais da ribeira

        landPathRegister.addLandPath(55551, 55553, 640.0, 45.0, 10.0,
                0.7,0.0); //Trindade - Clérigos

        landPathRegister.addLandPath(55553, 55551, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Trindade

        landPathRegister.addLandPath(55553, 55555, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Bolhão

        landPathRegister.addLandPath(55555, 55553, 634.0, 35.0, 6.0,
                0.7,0.0); //Bolhão - Clérigos

        landPathRegister.addLandPath(55553, 55554, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Majestic

        landPathRegister.addLandPath(55550, 55555, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Bolhão

        landPathRegister.addLandPath(55550, 55554, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Majestic
    }

    @AfterEach
    void tearDown() {

        platform.resetData();

        System.out.println(mapController.mapToString());

        landPathRegister.removeLandPath(55553,55554);
        landPathRegister.removeLandPath(55553,55555);
        landPathRegister.removeLandPath(55555,55550);
        landPathRegister.removeLandPath(55554,55550);

        landPathRegister.removeLandPath(55550,55551);
        landPathRegister.removeLandPath(55551,55550);

        landPathRegister.removeLandPath(55551,55553);
        landPathRegister.removeLandPath(55553,55551);

        landPathRegister.removeLandPath(55553,55555);
        landPathRegister.removeLandPath(55555,55553);

        landPathRegister.removeLandPath(55550,55555);
        landPathRegister.removeLandPath(55550,55554);
        landPathRegister.removeLandPath(55554,55550);

        geoLocationRegister.removeGeoLocation(55550);
        geoLocationRegister.removeGeoLocation(55551);
        geoLocationRegister.removeGeoLocation(55552);
        geoLocationRegister.removeGeoLocation(55553);
        geoLocationRegister.removeGeoLocation(55554);
        geoLocationRegister.removeGeoLocation(55555);
        geoLocationRegister.removeGeoLocation(55556);

        addressRegister.removeAddress(55550);
        addressRegister.removeAddress(55551);
        addressRegister.removeAddress(55552);
        addressRegister.removeAddress(55553);
        addressRegister.removeAddress(55554);
        addressRegister.removeAddress(55555);
        addressRegister.removeAddress(55556);
    }

    @Test
    void addConnection() {
        mapController.addAddress(addressRegister.getAddressByID(55550));
        mapController.addAddress(addressRegister.getAddressByID(55551));
        mapController.addAddress(addressRegister.getAddressByID(55552));
        mapController.addAddress(addressRegister.getAddressByID(55553));
        mapController.addAddress(addressRegister.getAddressByID(55554));
        mapController.addAddress(addressRegister.getAddressByID(55555));
        mapController.addAddress(addressRegister.getAddressByID(55556));

        LandPath path = landPathRegister.getPathByID(55553,55554);
        boolean actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(55553,55555);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(55554,55550);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(55555,55550);
        actual = mapController.addConnection(path);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        actual = mapController.addConnection(path);
        assertFalse(actual, "Should be false, because the connection already exists.");

        path = new LandPath(-1,-1,1.0,1.0, 2.0, 1.0, 0.0);

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
        mapController.addAddress(addressRegister.getAddressByID(55550));
        mapController.addAddress(addressRegister.getAddressByID(55551));
        mapController.addAddress(addressRegister.getAddressByID(55552));
        mapController.addAddress(addressRegister.getAddressByID(55553));
        mapController.addAddress(addressRegister.getAddressByID(55554));
        mapController.addAddress(addressRegister.getAddressByID(55555));
        mapController.addAddress(addressRegister.getAddressByID(55556));

        LandPath path = landPathRegister.getPathByID(55551,55553); //TRINDADE - CLERIGOS
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55553,55551); //CLERIGOS - TRINDADE
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55553,55555); //CLERIGOS - BOLHAO
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55555,55553); //BOLHAO - CLERIGOS
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55553,55554); //CLERIGOS - MAJESTIC
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55555,55550); //BOLHAO - CAIS DA RIBEIRA
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55550,55555); //CAIS DA RIBEIRA - BOLHAO
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55550,55554); //CAIS DA RIBEIRA - MAJESTIC
        mapController.addConnection(path);
        path = landPathRegister.getPathByID(55554,55550); //MAJESTIC- CAIS DA RIBEIRA
        mapController.addConnection(path);

        LinkedList<Address> shortestPath = new LinkedList<>();
        double expectedDistance = 2286.859;
        double actualDistance = mapController.getShortestRoute(addressRegister.getAddressByID(55551), addressRegister.getAddressByID(55550),shortestPath);
        System.out.println("Trindade -> Cais da Ribeira" + "\n" + shortestPath);
        List<Address> expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(55551));  //TRINDADE
        expectedPath.add(addressRegister.getAddressByID(55553));  //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(55554));  //MAJESTIC
        expectedPath.add(addressRegister.getAddressByID(55550));  //CAIS DA RIBEIRA

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath,shortestPath,"The paths should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.001, "The energy consumption should be 2402.088m !");

        shortestPath = new LinkedList<>();
        expectedDistance = 2402.088;
        actualDistance = mapController.getShortestRoute(addressRegister.getAddressByID(55550), addressRegister.getAddressByID(55551),shortestPath);
        System.out.println("Cais da Ribeira -> Trindade" + "\n" + shortestPath);
        expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(55550)); //CAIS DA RIBEIRA
        expectedPath.add(addressRegister.getAddressByID(55555)); //BOLHAO
        expectedPath.add(addressRegister.getAddressByID(55553)); //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(55551)); //TRINDADE

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath, shortestPath, "The path should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.001, "The energy consumption should be !");
    }
}