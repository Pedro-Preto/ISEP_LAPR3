package lapr.project.model.Registers;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.utils.EnergyConsumptionAlgorithms;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AerialPathRegisterTest {

    AerialPathRegister aerialPathRegister;
    AddressRegister addressRegister;
    GeoLocationRegister geoLocationRegister;

    @BeforeEach
    void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.resetData();
        aerialPathRegister = platform.getAerialPathRegister();
        addressRegister = platform.getAddressRegister();
        geoLocationRegister = platform.getGeoLocationRegister();

//        tearDown();

        addressRegister.addAddress(888, "Rua 888", "888", "City 888", "Coutry 888");
        addressRegister.addAddress(999, "Rua 999", "999", "City 999", "Coutry 999");

        geoLocationRegister.addGeoLocation(888,41.15227, -8.60929, 104,888);
        geoLocationRegister.addGeoLocation(999,41.16875, -8.68995, 4 ,999);
    }

    @AfterEach
    void tearDown() {
        aerialPathRegister.removeAerialPath(888,999);
        aerialPathRegister.removeAerialPath(999,888);

        geoLocationRegister.removeGeoLocation(888);
        geoLocationRegister.removeGeoLocation(999);

        addressRegister.removeAddress(888);
        addressRegister.removeAddress(999);



    }

    @Test
    void addAerialPath() {
        boolean actual = aerialPathRegister.addAerialPath(888,999,
                EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(geoLocationRegister.getGeolocationByAddressID(888),
                        geoLocationRegister.getGeolocationByAddressID(999)), 45.0, 6.0, 1534.2,0.7);

        assertTrue(actual, "The path should be add with sucess.");
        actual = aerialPathRegister.addAerialPath(888,999,
                EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(geoLocationRegister.getGeolocationByAddressID(888),
                        geoLocationRegister.getGeolocationByAddressID(999)), 45.0, 6.0, 1534.2,0.7);

        assertFalse(actual, "The path should'nt be added with sucess because there is already one equal.");

        boolean thrown = false;

        try{
            aerialPathRegister.addAerialPath(8888,999,
                    EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(geoLocationRegister.getGeolocationByAddressID(8888),
                            geoLocationRegister.getGeolocationByAddressID(999)), 45.0, 6.0, 1534.2,0.7);

        }catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            thrown = true;
        }

        assertTrue(thrown, "The path should'nt be added with success, and an exception should be thrown because the start geoLocation is invalid.");

    }

    @Test
    void removeAerialPath() {
        aerialPathRegister.addAerialPath(888,999,
                EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(geoLocationRegister.getGeolocationByAddressID(888),
                        geoLocationRegister.getGeolocationByAddressID(999)), 45.0, 6.0, 1534.2,0.7);
        aerialPathRegister.addAerialPath(999,888,
                EnergyConsumptionAlgorithms.calculateDistanceBetweenLocations(geoLocationRegister.getGeolocationByAddressID(999),
                        geoLocationRegister.getGeolocationByAddressID(888)), 45.0, 6.0, 1534.2,0.7);

        boolean actual = aerialPathRegister.removeAerialPath(888,999);
        assertTrue(actual,"The value should be removed sucessfully.");

        actual = aerialPathRegister.removeAerialPath(888,999);
        assertFalse(actual, "The value should be false because aerial path doesn't exist!");

        actual = aerialPathRegister.removeAerialPath(999,888);
        assertTrue(actual,"The value should be removed sucessfully.");    }
}