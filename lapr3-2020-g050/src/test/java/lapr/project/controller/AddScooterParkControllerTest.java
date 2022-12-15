package lapr.project.controller;

import lapr.project.data.ScooterParkDB;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.DomainClasses.ScooterPark;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AddScooterParkControllerTest {

    private AddScooterParkController addScooterParkController;
    private AddPharmacyController addPharmacyController;
    Platform platform;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        addScooterParkController = new AddScooterParkController();
        addPharmacyController = new AddPharmacyController();
        platform.getPharmacyRegister().removePharmacy("294784970");
        platform.getAddressRegister().removeAddress(20000);
        addPharmacyController.addAddress(20000, "Rua 200", "135", "Porto", "Portugal");
        addPharmacyController.addPharmacy("294784970", "Farmacia Torres", 20000);
    }

    @AfterEach
    void tearDown() {
        
        platform.getScooterParkRegister().removeScooterPark(20001);

        platform.getChargingStationRegister().removeChargingStation(70005);
        platform.getParkSlotRegister().removeParkSlot(70005);
        platform.getScooterParkRegister().removeScooterPark(60000);

        platform.getPharmacyRegister().removePharmacy("294784970");
        platform.getAddressRegister().removeAddress(20000);

    }

    @Test
    void addScooterPark() {

        boolean result = addScooterParkController.addScooterPark(20001, "294784970", 200.0);
        assertTrue(result, "It should be true! The Scooter park should be introduced");
        result = addScooterParkController.addScooterPark(20001, "294784970", 200.0);
        assertFalse(result, "It should be false, this scooter park already exists!");

        //Reset database

    }

    @Test
    void addParkSlot() {

        addScooterParkController.addScooterPark(60000, "294784970", 200.0);
        boolean result = addScooterParkController.addParkSlot(70005);
        assertTrue(result, "It should be true! The park slot should be introduced");
        result = addScooterParkController.addParkSlot(70005);
        assertFalse(result, "It should be false! The park slot already exists.");

    }

    @Test
    void addChargingStation() {

        addScooterParkController.addScooterPark(60000, "294784970", 200.0);
        addScooterParkController.addParkSlot(70005);
        boolean result = addScooterParkController.addChargingStation(70005, 40.3, 20.0);
        assertTrue(result, "It should be true! The charging station should be introduced");
        result = addScooterParkController.addChargingStation(70005, 40.3, 20.0);
        assertFalse(result, "It should be true! The charging station already exists");
    }


}


