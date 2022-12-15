package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AddDroneParkControllerTest {

    private AddDroneParkController addDroneParkController;
    AddPharmacyController addPharmacyController;
    Platform platform;

    @BeforeEach
    void setUp() {
        addDroneParkController = new AddDroneParkController();
        platform = ApplicationEM.getInstance().getPlatform();
        addPharmacyController = new AddPharmacyController();
        addPharmacyController.addAddress(2000, "Rua 6", "135", "Porto", "Portugal");
        addPharmacyController.addPharmacy("torre211", "Farmacia Torres", 2000);
    }

    @AfterEach
    void tearDown() {
        platform.getDroneParkRegister().removeDronePark(201);

        platform.getParkSlotRegister().removeParkSlot(705);
        platform.getDroneParkRegister().removeDronePark(600);

        platform.getChargingStationRegister().removeChargingStation(705);
        platform.getParkSlotRegister().removeParkSlot(705);
        platform.getDroneParkRegister().removeDronePark(600);

        platform.getPharmacyRegister().removePharmacy("torre211");
        platform.getAddressRegister().removeAddress(2000);
    }


    @Test
    void addDronePark() {

        boolean result = addDroneParkController.addDronePark(201,"torre211",200.0);
        assertTrue(result, "It should be true! The drone park should be introduced");
        result = addDroneParkController.addDronePark(201,"torre211", 200.0);
        assertFalse(result, "It should be false, this drone park already exists!");

        //Reset database

    }

    @Test
    void addParkSlot() {
        addDroneParkController.addDronePark(600,"torre211", 200.0);
        boolean result = addDroneParkController.addParkSlot(705, 600);
        assertTrue(result, "It should be true! The park slot should be introduced");
        result = addDroneParkController.addParkSlot(705, 600);
        assertFalse(result, "It should be false! The park slot already exists.");

    }

    @Test
    void addChargingStation() {
        addDroneParkController.addDronePark(600,"torre211", 200.0);
        addDroneParkController.addParkSlot(705, 600);
        boolean result = addDroneParkController.addChargingStation(705,40.3,20.0);
        assertTrue(result, "It should be true! The charging station should be introduced");
        result = addDroneParkController.addChargingStation(705,40.3,20.0);
        assertFalse(result, "It should be true! The charging station already exists");

    }


}