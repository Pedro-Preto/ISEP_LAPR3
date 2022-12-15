package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.GeoLocationRegister;
import lapr.project.model.Registers.LandPathRegister;
import lapr.project.utils.EnergyConsumptionAlgorithms;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapControllerTest {

    MapController mapController;
    Platform platform;
    LandPathRegister landPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;

    Scooter scooter = new Scooter();
    Courier courier = new Courier();

    @BeforeEach
    void setUp() {
        mapController = new MapController();
        platform = ApplicationEM.getInstance().getPlatform();

        platform.resetData();

        landPathRegister = platform.getLandPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(88880,"Cais da Ribeira", "88880", "Porto", "Portugal");
        addressRegister.addAddress(88881,"Trindade", "88881", "Porto", "Portugal");
        addressRegister.addAddress(88882,"Castelo do Queijo", "88882", "Porto", "Portugal");
        addressRegister.addAddress(88883,"Clérigos", "88883", "Porto", "Portugal");
        addressRegister.addAddress(88884,"Majestic", "88884", "Porto", "Portugal");
        addressRegister.addAddress(88885,"Bolhão", "88885", "Porto", "Portugal");
        addressRegister.addAddress(88886,"Sé", "88886", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(88880,41.14063, -8.61118, 25,88880);
        geoLocationRegister.addGeoLocation(88881,41.15227, -8.60929, 104,88881);
        geoLocationRegister.addGeoLocation(88882,41.16875, -8.68995, 4,88882);
        geoLocationRegister.addGeoLocation(88883,41.14582, -8.61398, 87,88883);
        geoLocationRegister.addGeoLocation(88884,41.14723, -8.60657, 91,88884);
        geoLocationRegister.addGeoLocation(88885,41.14871, -8.60746, 87 ,88885);
        geoLocationRegister.addGeoLocation(88886,41.14331, -8.60914, 82,88886);

        landPathRegister.addLandPath(88883, 88884, 640.0, 45.0, 10.0,
                0.7,0.0); //Clerigos - Majestic

        landPathRegister.addLandPath(88883, 88885, 634.0, 35.0, 6.0,
                0.7,0.0); //Clerigos - Bolhão

        landPathRegister.addLandPath(88885, 88880, 951.0, 35.0, 4.0,
                0.7,0.0); //Bolhão - Cais da ribeira

        landPathRegister.addLandPath(88884, 88880, 829.0, 15.0, 14.0,
                0.7,0.0); //Majestic - Cais da ribeira

        landPathRegister.addLandPath(88880, 88881, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Trindade

        landPathRegister.addLandPath(88881, 88880, 634.0, 35.0, 6.0,
                0.7,0.0); //Trindade - Cais da ribeira

        landPathRegister.addLandPath(88881, 88883, 640.0, 45.0, 10.0,
                0.7,0.0); //Trindade - Clérigos

        landPathRegister.addLandPath(88883, 88881, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Trindade

        landPathRegister.addLandPath(88883, 88885, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Bolhão

        landPathRegister.addLandPath(88885, 88883, 634.0, 35.0, 6.0,
                0.7,0.0); //Bolhão - Clérigos

        landPathRegister.addLandPath(88883, 88884, 634.0, 35.0, 6.0,
                0.7,0.0); //Clérigos - Majestic

        landPathRegister.addLandPath(88880, 88885, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Bolhão

        landPathRegister.addLandPath(88880, 88884, 640.0, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Majestic
    }

    @AfterEach
    void tearDown() {

        platform.resetData();

        System.out.println(mapController.mapToString());

        landPathRegister.removeLandPath(88883,88884);
        landPathRegister.removeLandPath(88883,88885);
        landPathRegister.removeLandPath(88885,88880);
        landPathRegister.removeLandPath(88884,88880);

        landPathRegister.removeLandPath(88880,88881);
        landPathRegister.removeLandPath(88881,88880);

        landPathRegister.removeLandPath(88881,88883);
        landPathRegister.removeLandPath(88883,88881);

        landPathRegister.removeLandPath(88883,88885);
        landPathRegister.removeLandPath(88885,88883);

        landPathRegister.removeLandPath(88880,88885);
        landPathRegister.removeLandPath(88880,88884);

        geoLocationRegister.removeGeoLocation(88880);
        geoLocationRegister.removeGeoLocation(88881);
        geoLocationRegister.removeGeoLocation(88882);
        geoLocationRegister.removeGeoLocation(88883);
        geoLocationRegister.removeGeoLocation(88884);
        geoLocationRegister.removeGeoLocation(88885);
        geoLocationRegister.removeGeoLocation(88886);

        addressRegister.removeAddress(88880);
        addressRegister.removeAddress(88881);
        addressRegister.removeAddress(88882);
        addressRegister.removeAddress(88883);
        addressRegister.removeAddress(88884);
        addressRegister.removeAddress(88885);
        addressRegister.removeAddress(88886);
    }

    @Test
    void addAddress() {

        Address address = addressRegister.getAddressByID(88880);
        boolean actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88881);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88882);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88883);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88884);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88885);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88886);
        actual = mapController.addAddress(address);
        assertTrue(actual, "Should be true because there isn't any location with this id on the map.");

        address = addressRegister.getAddressByID(88881);
        actual = mapController.addAddress(address);
        assertFalse(actual, "Should be true because there already exists a location with this id on the map.");

        actual = mapController.addAddress(null);
        assertFalse(actual, "Should be false becuase the address was null.");

    }

    @Test
    void mapToString() {
        LocationNet locationNet = new LocationNet();
        String expected = locationNet.mapToString();
        String actual = mapController.mapToString();

        assertEquals(expected, actual, "Should be the same.");
    }

//    @Test
//    void addConnection() {
//        mapController.addAddress(addressRegister.getAddressByID(88880));
//        mapController.addAddress(addressRegister.getAddressByID(88881));
//        mapController.addAddress(addressRegister.getAddressByID(88882));
//        mapController.addAddress(addressRegister.getAddressByID(88883));
//        mapController.addAddress(addressRegister.getAddressByID(88884));
//        mapController.addAddress(addressRegister.getAddressByID(88885));
//        mapController.addAddress(addressRegister.getAddressByID(88886));
//
//        LandPath path = landPathRegister.getPathByID(88883,88884);
//        boolean actual = mapController.addConnection(path, scooter, courier);
//        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");
//
//        path = landPathRegister.getPathByID(88883,88885);
//        actual = mapController.addConnection(path, scooter, courier);
//        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");
//
//        path = landPathRegister.getPathByID(88884,88880);
//        actual = mapController.addConnection(path, scooter, courier);
//        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");
//
//        path = landPathRegister.getPathByID(88885,88880);
//        actual = mapController.addConnection(path, scooter, courier);
//        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");
//
//        actual = mapController.addConnection(path, scooter, courier);
//        assertFalse(actual, "Should be false, because the connection already exists.");
//
//        path = new LandPath(-1,-1,1.0,1.0, 2.0, 1.0, 0.0);
//
//        boolean thrown = false;
//        try {
//            mapController.addConnection(path, scooter, courier);
//        } catch (IllegalArgumentException ex) {
//            thrown = true;
//        }
//        assertTrue(thrown, "Should be thrown the exception, because the adddress id's are invalid.");
//
//        actual = mapController.addConnection(null,null,null);
//        assertFalse(actual, "Should be false, because the path is null.");
//
//    }
//
//    @Test
//    void getMostEfficientRoute() {
//        mapController.addAddress(addressRegister.getAddressByID(88880));
//        mapController.addAddress(addressRegister.getAddressByID(88881));
//        mapController.addAddress(addressRegister.getAddressByID(88882));
//        mapController.addAddress(addressRegister.getAddressByID(88883));
//        mapController.addAddress(addressRegister.getAddressByID(88884));
//        mapController.addAddress(addressRegister.getAddressByID(88885));
//        mapController.addAddress(addressRegister.getAddressByID(88886));
//
//        LandPath path = landPathRegister.getPathByID(88881,88883); //TRINDADE - CLERIGOS
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88883,88881); //CLERIGOS - TRINDADE
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88883,88885); //CLERIGOS - BOLHAO
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88885,88883); //BOLHAO - CLERIGOS
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88883,88884); //CLERIGOS - MAJESTIC
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88885,88880); //BOLHAO - CAIS DA RIBEIRA
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88880,88885); //CAIS DA RIBEIRA - BOLHAO
//        mapController.addConnection(path, scooter, courier);
//        path = landPathRegister.getPathByID(88880,88884); //CAIS DA RIBEIRA - MAJESTIC
//        mapController.addConnection(path, scooter, courier);
//
//        LinkedList<Address> shortestPath = new LinkedList<>();
//        double expectedEnergyConsumption = 3816385.189;
//        double actualEnergyConsumption = mapController.getMostEfficientRoute(addressRegister.getAddressByID(88881), addressRegister.getAddressByID(88880),shortestPath);
//        System.out.println("Trindade -> Cais da Ribeira" + "\n" + shortestPath);
//        List<Address> expectedPath = new LinkedList<>();
//        expectedPath.add(addressRegister.getAddressByID(88881));
//        expectedPath.add(addressRegister.getAddressByID(88883));
//        expectedPath.add(addressRegister.getAddressByID(88885));
//        expectedPath.add(addressRegister.getAddressByID(88880));
//
//        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
//        assertEquals(expectedPath,shortestPath,"The paths should be the same.");
//        assertEquals(expectedEnergyConsumption, actualEnergyConsumption, 0.001, "The energy consumption should be 7451767.784 !");
//
//        shortestPath = new LinkedList<>();
//        expectedEnergyConsumption = 4073639.885;
//        actualEnergyConsumption = mapController.getMostEfficientRoute(addressRegister.getAddressByID(88880), addressRegister.getAddressByID(88881),shortestPath);
//        System.out.println("Cais da Ribeira -> Trindade" + "\n" + shortestPath);
//        expectedPath = new LinkedList<>();
//        expectedPath.add(addressRegister.getAddressByID(88880));
//        expectedPath.add(addressRegister.getAddressByID(88885));
//        expectedPath.add(addressRegister.getAddressByID(88883));
//        expectedPath.add(addressRegister.getAddressByID(88881));
//
//        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
//        assertEquals(expectedPath, shortestPath, "The path should be the same.");
//        assertEquals(expectedEnergyConsumption, actualEnergyConsumption, 0.001, "The energy consumption should be !");
//    }
}