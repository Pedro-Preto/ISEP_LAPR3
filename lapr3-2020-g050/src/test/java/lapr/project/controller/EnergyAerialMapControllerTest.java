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

class EnergyAerialMapControllerTest {

    EnergyAerialMapController mapController;
    Platform platform;
    AerialPathRegister aerialPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;

    Drone drone = new Drone();

    @BeforeEach
    void setUp() {
        mapController = new EnergyAerialMapController();
        platform = ApplicationEM.getInstance().getPlatform();

        platform.resetData();

        aerialPathRegister = platform.getAerialPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(66660, "Cais da Ribeira", "66660", "Porto", "Portugal");
        addressRegister.addAddress(66661, "Trindade", "66661", "Porto", "Portugal");
        addressRegister.addAddress(66662, "Castelo do Queijo", "66662", "Porto", "Portugal");
        addressRegister.addAddress(66663, "Clérigos", "66663", "Porto", "Portugal");
        addressRegister.addAddress(66664, "Majestic", "66664", "Porto", "Portugal");
        addressRegister.addAddress(66665, "Bolhão", "66665", "Porto", "Portugal");
        addressRegister.addAddress(66666, "Sé", "66666", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(66660, 41.14063, -8.61118, 25, 66660);
        geoLocationRegister.addGeoLocation(66661, 41.15227, -8.60929, 104, 66661);
        geoLocationRegister.addGeoLocation(66662, 41.16875, -8.68995, 4, 66662);
        geoLocationRegister.addGeoLocation(66663, 41.14582, -8.61398, 87, 66663);
        geoLocationRegister.addGeoLocation(66664, 41.14723, -8.60657, 91, 66664);
        geoLocationRegister.addGeoLocation(66665, 41.14871, -8.60746, 87, 66665);
        geoLocationRegister.addGeoLocation(66666, 41.14331, -8.60914, 82, 66666);

        aerialPathRegister.addAerialPath(66663, 66664, 640.0, 45.0, 10.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT); //Clerigos - Majestic

        aerialPathRegister.addAerialPath(66663, 66665, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Clerigos - Bolhão

        aerialPathRegister.addAerialPath(66665, 66660, 951.0, 35.0, 4.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Bolhão - Cais da ribeira

        aerialPathRegister.addAerialPath(66664, 66660, 829.0, 15.0, 14.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT); //Majestic - Cais da ribeira

        aerialPathRegister.addAerialPath(66660, 66661, 640.0, 45.0, 10.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Cais da ribeira - Trindade

        aerialPathRegister.addAerialPath(66661, 66660, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Trindade - Cais da ribeira

        aerialPathRegister.addAerialPath(66661, 66663, 640.0, 45.0, 10.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Trindade - Clérigos

        aerialPathRegister.addAerialPath(66663, 66661, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT); //Clérigos - Trindade

        aerialPathRegister.addAerialPath(66663, 66665, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Clérigos - Bolhão

        aerialPathRegister.addAerialPath(66665, 66663, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Bolhão - Clérigos

        aerialPathRegister.addAerialPath(66663, 66664, 634.0, 35.0, 6.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Clérigos - Majestic

        aerialPathRegister.addAerialPath(66660, 66665, 640.0, 45.0, 10.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Cais da ribeira - Bolhão

        aerialPathRegister.addAerialPath(66660, 66664, 640.0, 45.0, 10.0,
                Constants.D_AIR_DENSITY, Constants.D_AIR_DRAG_COEFFICIENT);  //Cais da ribeira - Majestic

    }

    @AfterEach
    void tearDown() {

        platform.resetData();

        System.out.println(mapController.mapToString());

        aerialPathRegister.removeAerialPath(66663, 66664);
        aerialPathRegister.removeAerialPath(66663, 66665);
        aerialPathRegister.removeAerialPath(66665, 66660);
        aerialPathRegister.removeAerialPath(66664, 66660);

        aerialPathRegister.removeAerialPath(66660, 66661);
        aerialPathRegister.removeAerialPath(66661, 66660);

        aerialPathRegister.removeAerialPath(66661, 66663);
        aerialPathRegister.removeAerialPath(66663, 66661);

        aerialPathRegister.removeAerialPath(66663, 66665);
        aerialPathRegister.removeAerialPath(66665, 66663);

        aerialPathRegister.removeAerialPath(66660, 66665);
        aerialPathRegister.removeAerialPath(66660, 66664);
        aerialPathRegister.removeAerialPath(66664, 66660);

        geoLocationRegister.removeGeoLocation(66660);
        geoLocationRegister.removeGeoLocation(66661);
        geoLocationRegister.removeGeoLocation(66662);
        geoLocationRegister.removeGeoLocation(66663);
        geoLocationRegister.removeGeoLocation(66664);
        geoLocationRegister.removeGeoLocation(66665);
        geoLocationRegister.removeGeoLocation(66666);

        addressRegister.removeAddress(66660);
        addressRegister.removeAddress(66661);
        addressRegister.removeAddress(66662);
        addressRegister.removeAddress(66663);
        addressRegister.removeAddress(66664);
        addressRegister.removeAddress(66665);
        addressRegister.removeAddress(66666);
    }

    @Test
    void addConnection() {
        mapController.addAddress(addressRegister.getAddressByID(66660));
        mapController.addAddress(addressRegister.getAddressByID(66661));
        mapController.addAddress(addressRegister.getAddressByID(66662));
        mapController.addAddress(addressRegister.getAddressByID(66663));
        mapController.addAddress(addressRegister.getAddressByID(66664));
        mapController.addAddress(addressRegister.getAddressByID(66665));
        mapController.addAddress(addressRegister.getAddressByID(66666));

        AerialPath path = aerialPathRegister.getPathByID(66663, 66664);
        boolean actual = mapController.addConnection(path, drone);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(66663, 66665);
        actual = mapController.addConnection(path, drone);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(66664, 66660);
        actual = mapController.addConnection(path, drone);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = aerialPathRegister.getPathByID(66665, 66660);
        actual = mapController.addConnection(path, drone);
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        actual = mapController.addConnection(path, drone);
        assertFalse(actual, "Should be false, because the connection already exists.");

        path = new AerialPath(-1, -1, 1.0, 1.0, 2.0, 1.0, 0.0);

        boolean thrown = false;
        try {
            mapController.addConnection(path, drone);
        } catch (IllegalArgumentException ex) {
            thrown = true;
        }
        assertTrue(thrown, "Should be thrown the exception, because the adddress id's are invalid.");

        actual = mapController.addConnection(null, null);
        assertFalse(actual, "Should be false, because the path is null.");

    }

    @Test
    void getMostEfficientRoute() {
        mapController.addAddress(addressRegister.getAddressByID(66660));
        mapController.addAddress(addressRegister.getAddressByID(66661));
        mapController.addAddress(addressRegister.getAddressByID(66662));
        mapController.addAddress(addressRegister.getAddressByID(66663));
        mapController.addAddress(addressRegister.getAddressByID(66664));
        mapController.addAddress(addressRegister.getAddressByID(66665));
        mapController.addAddress(addressRegister.getAddressByID(66666));

        AerialPath path = aerialPathRegister.getPathByID(66661, 66663); //TRINDADE - CLERIGOS
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66663, 66661); //CLERIGOS - TRINDADE
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66663, 66665); //CLERIGOS - BOLHAO
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66665, 66663); //BOLHAO - CLERIGOS
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66663, 66664); //CLERIGOS - MAJESTIC
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66665, 66660); //BOLHAO - CAIS DA RIBEIRA
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66660, 66665); //CAIS DA RIBEIRA - BOLHAO
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66660, 66664); //CAIS DA RIBEIRA - MAJESTIC
        mapController.addConnection(path, drone);
        path = aerialPathRegister.getPathByID(66660, 66664); //MAJESTIC - CAIS DA RIBEIRA
        mapController.addConnection(path, drone);

        LinkedList<Address> shortestPath = new LinkedList<>();
        double expectedEnergyConsumption = 272194.344;
        double actualEnergyConsumption = mapController.getMostEfficientRoute(addressRegister.getAddressByID(66661), addressRegister.getAddressByID(66660), shortestPath);
        System.out.println("Trindade -> Cais da Ribeira" + "\n" + shortestPath);
        List<Address> expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(66661));
        expectedPath.add(addressRegister.getAddressByID(66663));
        expectedPath.add(addressRegister.getAddressByID(66665));
        expectedPath.add(addressRegister.getAddressByID(66660));

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath, shortestPath, "The paths should be the same.");
        assertEquals(expectedEnergyConsumption, actualEnergyConsumption, 0.001, "The energy consumption should be 272194.3437 J !");

        shortestPath = new LinkedList<>();
        expectedEnergyConsumption = 295125.461;
        actualEnergyConsumption = mapController.getMostEfficientRoute(addressRegister.getAddressByID(66660), addressRegister.getAddressByID(66661), shortestPath);
        System.out.println("Cais da Ribeira -> Trindade" + "\n" + shortestPath);
        expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(66660));
        expectedPath.add(addressRegister.getAddressByID(66665));
        expectedPath.add(addressRegister.getAddressByID(66663));
        expectedPath.add(addressRegister.getAddressByID(66661));

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath, shortestPath, "The path should be the same.");
        assertEquals(expectedEnergyConsumption, actualEnergyConsumption, 0.001, "The energy consumption should be 295125.461 J !");
    }
}