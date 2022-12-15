package lapr.project.model.DomainClasses;

import lapr.project.controller.ApplicationEM;
import lapr.project.controller.DistanceLandMapController;
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

class LocationNetTest {

    Platform platform;
    LandPathRegister landPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;
    LocationNet locationNet;


    @BeforeEach
    void setUp() {
        locationNet = new LocationNet();
        platform = ApplicationEM.getInstance().getPlatform();

        platform.resetData();

        landPathRegister = platform.getLandPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

        addressRegister.addAddress(77770,"Cais da Ribeira", "77770", "Porto", "Portugal");
        addressRegister.addAddress(77771,"Trindade", "77771", "Porto", "Portugal");
        addressRegister.addAddress(77772,"Castelo do Queijo", "77772", "Porto", "Portugal");
        addressRegister.addAddress(77773,"Clérigos", "77773", "Porto", "Portugal");
        addressRegister.addAddress(77774,"Majestic", "77774", "Porto", "Portugal");
        addressRegister.addAddress(77775,"Bolhão", "77775", "Porto", "Portugal");
        addressRegister.addAddress(77776,"Sé", "77776", "Porto", "Portugal");

        geoLocationRegister.addGeoLocation(77770,41.14063, -8.61118, 25,77770);
        geoLocationRegister.addGeoLocation(77771,41.15227, -8.60929, 104,77771);
        geoLocationRegister.addGeoLocation(77772,41.16875, -8.68995, 4,77772);
        geoLocationRegister.addGeoLocation(77773,41.14582, -8.61398, 87,77773);
        geoLocationRegister.addGeoLocation(77774,41.14723, -8.60657, 91,77774);
        geoLocationRegister.addGeoLocation(77775,41.14871, -8.60746, 87 ,77775);
        geoLocationRegister.addGeoLocation(77776,41.14331, -8.60914, 82,77776);


        double distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77773),geoLocationRegister.getGeolocationByID(77774));
        landPathRegister.addLandPath(77773, 77774, distance, 45.0, 10.0,
                0.7,0.0); //Clerigos - Majestic

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77773),geoLocationRegister.getGeolocationByID(77775));
        landPathRegister.addLandPath(77773, 77775, distance, 35.0, 6.0,
                0.7,0.0); //Clerigos - Bolhão

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77775),geoLocationRegister.getGeolocationByID(77770));
        landPathRegister.addLandPath(77775, 77770, distance, 35.0, 4.0,
                0.7,0.0); //Bolhão - Cais da ribeira

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77774),geoLocationRegister.getGeolocationByID(77770));
        landPathRegister.addLandPath(77774, 77770, distance, 15.0, 14.0,
                0.7,0.0); //Majestic - Cais da ribeira

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77770),geoLocationRegister.getGeolocationByID(77771));
        landPathRegister.addLandPath(77770, 77771, distance, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Trindade

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77771),geoLocationRegister.getGeolocationByID(77770));
        landPathRegister.addLandPath(77771, 77770, distance, 35.0, 6.0,
                0.7,0.0); //Trindade - Cais da ribeira

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77771),geoLocationRegister.getGeolocationByID(77773));
        landPathRegister.addLandPath(77771, 77773, distance, 45.0, 10.0,
                0.7,0.0); //Trindade - Clérigos

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77773),geoLocationRegister.getGeolocationByID(77771));
        landPathRegister.addLandPath(77773, 77771, distance, 35.0, 6.0,
                0.7,0.0); //Clérigos - Trindade

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77773),geoLocationRegister.getGeolocationByID(77775));
        landPathRegister.addLandPath(77773, 77775, distance, 35.0, 6.0,
                0.7,0.0); //Clérigos - Bolhão

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77775),geoLocationRegister.getGeolocationByID(77773));
        landPathRegister.addLandPath(77775, 77773, distance, 35.0, 6.0,
                0.7,0.0); //Bolhão - Clérigos

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77773),geoLocationRegister.getGeolocationByID(77774));
        landPathRegister.addLandPath(77773, 77774, distance, 35.0, 6.0,
                0.7,0.0); //Clérigos - Majestic

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77770),geoLocationRegister.getGeolocationByID(77775));
        landPathRegister.addLandPath(77770, 77775, distance, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Bolhão

        distance = EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations
                (geoLocationRegister.getGeolocationByAddressID(77770),geoLocationRegister.getGeolocationByID(77774));
        landPathRegister.addLandPath(77770, 77774, distance, 45.0, 10.0,
                0.7,0.0); //Cais da ribeira - Majestic
    }

    @AfterEach
    void tearDown() {

        platform.resetData();

        System.out.println(locationNet.mapToString());

        landPathRegister.removeLandPath(77773,77774);
        landPathRegister.removeLandPath(77773,77775);
        landPathRegister.removeLandPath(77775,77770);
        landPathRegister.removeLandPath(77774,77770);

        landPathRegister.removeLandPath(77770,77771);
        landPathRegister.removeLandPath(77771,77770);

        landPathRegister.removeLandPath(77771,77773);
        landPathRegister.removeLandPath(77773,77771);

        landPathRegister.removeLandPath(77773,77775);
        landPathRegister.removeLandPath(77775,77773);

        landPathRegister.removeLandPath(77770,77775);
        landPathRegister.removeLandPath(77770,77774);
        landPathRegister.removeLandPath(77774,77770);

        geoLocationRegister.removeGeoLocation(77770);
        geoLocationRegister.removeGeoLocation(77771);
        geoLocationRegister.removeGeoLocation(77772);
        geoLocationRegister.removeGeoLocation(77773);
        geoLocationRegister.removeGeoLocation(77774);
        geoLocationRegister.removeGeoLocation(77775);
        geoLocationRegister.removeGeoLocation(77776);

        addressRegister.removeAddress(77770);
        addressRegister.removeAddress(77771);
        addressRegister.removeAddress(77772);
        addressRegister.removeAddress(77773);
        addressRegister.removeAddress(77774);
        addressRegister.removeAddress(77775);
        addressRegister.removeAddress(77776);
    }

    @Test
    void addConnection() {
        locationNet.addAddress(addressRegister.getAddressByID(77770));
        locationNet.addAddress(addressRegister.getAddressByID(77771));
        locationNet.addAddress(addressRegister.getAddressByID(77772));
        locationNet.addAddress(addressRegister.getAddressByID(77773));
        locationNet.addAddress(addressRegister.getAddressByID(77774));
        locationNet.addAddress(addressRegister.getAddressByID(77775));
        locationNet.addAddress(addressRegister.getAddressByID(77776));

        LandPath path = landPathRegister.getPathByID(77773,77774);
        Address startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        Address endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        boolean actual = locationNet.addConnection(startAddress,endAddress,path.getDistance());
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(77773,77775);
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        actual = locationNet.addConnection(startAddress,endAddress,path.getDistance());
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(77774,77770);
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        actual = locationNet.addConnection(startAddress,endAddress,path.getDistance());
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        path = landPathRegister.getPathByID(77775,77770);
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        actual = locationNet.addConnection(startAddress,endAddress,path.getDistance());
        assertTrue(actual, "Should be true, because the connection doesn't exist yet and the addresses are valid.");

        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        actual = locationNet.addConnection(startAddress,endAddress,path.getDistance());
        assertFalse(actual, "Should be false, because the connection already exists.");

        path = new LandPath(-1,-1,1.0,1.0, 2.0, 1.0, 0.0);

        boolean thrown = false;
        try {
            startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
            endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
            locationNet.addConnection(startAddress,endAddress,path.getDistance());
        } catch (IllegalArgumentException ex) {
            thrown = true;
        }
        assertTrue(thrown, "Should be thrown the exception, because the adddress id's are invalid.");

        actual = locationNet.addConnection(null,null,0.0);
        assertFalse(actual, "Should be false, because the path is null.");
    }

    @Test
    void getRoute() {
        locationNet.addAddress(addressRegister.getAddressByID(77770));
        locationNet.addAddress(addressRegister.getAddressByID(77771));
        locationNet.addAddress(addressRegister.getAddressByID(77772));
        locationNet.addAddress(addressRegister.getAddressByID(77773));
        locationNet.addAddress(addressRegister.getAddressByID(77774));
        locationNet.addAddress(addressRegister.getAddressByID(77775));
        locationNet.addAddress(addressRegister.getAddressByID(77776));

        LandPath path = landPathRegister.getPathByID(77771,77773); //TRINDADE - CLERIGOS
        Address startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        Address endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77773,77771); //CLERIGOS - TRINDADE
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77773,77775); //CLERIGOS - BOLHAO
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77775,77773); //BOLHAO - CLERIGOS
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77773,77774); //CLERIGOS - MAJESTIC
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77775,77770); //BOLHAO - CAIS DA RIBEIRA
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77770,77775); //CAIS DA RIBEIRA - BOLHAO
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77770,77774); //CAIS DA RIBEIRA - MAJESTIC
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77774,77770); //MAJESTIC- CAIS DA RIBEIRA
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        LinkedList<Address> shortestPath = new LinkedList<>();
        double expectedDistance = 2286.859;
        double actualDistance = locationNet.getRoute(addressRegister.getAddressByID(77771), addressRegister.getAddressByID(77770),shortestPath);
        System.out.println("Trindade -> Cais da Ribeira" + "\n" + shortestPath);
        List<Address> expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(77771));  //TRINDADE
        expectedPath.add(addressRegister.getAddressByID(77773));  //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(77774));  //MAJESTIC
        expectedPath.add(addressRegister.getAddressByID(77770));  //CAIS DA RIBEIRA

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath,shortestPath,"The paths should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.001, "The energy consumption should be 2402.088m !");

        shortestPath = new LinkedList<>();
        expectedDistance = 2402.088;
        actualDistance = locationNet.getRoute(addressRegister.getAddressByID(77770), addressRegister.getAddressByID(77771),shortestPath);
        System.out.println("Cais da Ribeira -> Trindade" + "\n" + shortestPath);
        expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(77770)); //CAIS DA RIBEIRA
        expectedPath.add(addressRegister.getAddressByID(77775)); //BOLHAO
        expectedPath.add(addressRegister.getAddressByID(77773)); //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(77771)); //TRINDADE

        assertEquals(shortestPath.size(), 4, "The path should have 4 address.");
        assertEquals(expectedPath, shortestPath, "The path should be the same.");
        assertEquals(expectedDistance, actualDistance, 0.01, "The energy consumption should be !");
    }

    
    @Test
    void getRouteWithIntermediatePoints() {
        locationNet.addAddress(addressRegister.getAddressByID(77770));
        locationNet.addAddress(addressRegister.getAddressByID(77771));
        locationNet.addAddress(addressRegister.getAddressByID(77772));
        locationNet.addAddress(addressRegister.getAddressByID(77773));
        locationNet.addAddress(addressRegister.getAddressByID(77774));
        locationNet.addAddress(addressRegister.getAddressByID(77775));
        locationNet.addAddress(addressRegister.getAddressByID(77776));

        LandPath path = landPathRegister.getPathByID(77771,77773); //TRINDADE - CLERIGOS
        Address startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        Address endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77773,77775); //CLERIGOS - BOLHAO
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77773,77774); //CLERIGOS - MAJESTIC
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77775,77770); //BOLHAO - CAIS DA RIBEIRA
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77770,77775); //CAIS DA RIBEIRA - BOLHAO
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77774,77770); //MAJESTIC- CAIS DA RIBEIRA
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        path = landPathRegister.getPathByID(77770,77774); //CAIS DA RIBEIRA - MAJESTIC
        startAddress = addressRegister.getAddressByID(path.getGeoLocationStart());
        endAddress = addressRegister.getAddressByID(path.getGeoLocationEnd());
        locationNet.addConnection(startAddress,endAddress,path.getDistance());

        System.out.println(locationNet.mapToString());

        LinkedList<Address> intermediate_path = new LinkedList<>();
        intermediate_path.add(addressRegister.getAddressByID(77775)); //BOLHÃO
        intermediate_path.add(addressRegister.getAddressByID(77770)); //CAIS DA RIBEIRA


        LinkedList<Address> actualPath = new LinkedList<>();
        Address origin = addressRegister.getAddressByID(77771); //TRINDADE
        double expected = 2402.09;
        double actual = locationNet.getRouteWithIntermediatePoints(origin,intermediate_path, actualPath);

        System.out.println(actual + " - \n" + actualPath);

        LinkedList<Address> expectedPath = new LinkedList<>();
        expectedPath.add(addressRegister.getAddressByID(77771)); //TRINDADE
        expectedPath.add(addressRegister.getAddressByID(77773)); //CLERIGOS
        expectedPath.add(addressRegister.getAddressByID(77775)); //BOLHAO
        expectedPath.add(addressRegister.getAddressByID(77770)); //CAIS DA RIBEIRA

        assertTrue(actualPath.size()==4,"The path should have 4 address");
        assertEquals(expectedPath, actualPath, "The path should be TRINDADE - CLERIGOS - BOLHAO - CAIS DA RIBEIRA.");
        assertEquals(expected, actual, "The actual value should be 2402.09 m");

        actualPath = new LinkedList<>();
        Address pharmacy = addressRegister.getAddressByID(77770); //CAIS DA RIBEIRA
        expected = -1.0;
        actual = locationNet.getRoute(pharmacy, origin, actualPath);

        System.out.println(actual + " - \n" + actualPath);

        expectedPath = new LinkedList<>();

        assertTrue(actualPath.size()==0,"The path should have 0 address");
        assertEquals(expectedPath, actualPath, "The path should be empty.");
        assertEquals(expected, actual, "The actual value should be -1, in other words, not possible");
    }
}