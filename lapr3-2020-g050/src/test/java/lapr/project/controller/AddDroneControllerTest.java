package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddDroneControllerTest {

    private AddDroneController addDroneController;
    private Platform platform;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController phar = new AddPharmacyController();
        phar.addAddress(9009990, "Rua dos Campeões", "15", "Lisboa", "Portugal");
        platform.getPharmacyRegister().addPharmacy("141414", "Farmácia do Cansaço", 9009990);
    }

    @BeforeEach
    void setUpControllers(){
        addDroneController = new AddDroneController();
        platform = ApplicationEM.getInstance().getPlatform();
    }
    @Test
    void addDrone() {
        boolean actual;
        actual = addDroneController.addDrone(31313121,1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,"141414",1.0);
        assertTrue(actual,"Must be added");
        actual = addDroneController.addDrone(31313121,1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,"141414",1.0);
        assertFalse(actual,"Must not be added");
        platform.getDroneRegister().removeDrone(31313121);
        actual = addDroneController.addDrone(31313121,1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,"141414",1.0);
        assertTrue(actual,"Must be added");
        platform.getDroneRegister().removeDrone(31313121);
    }
    @AfterAll
    static void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getDroneRegister().removeDrone(31313121);
        platform.getPharmacyRegister().removePharmacy("141414");
        platform.getAddressRegister().removeAddress(9009990);
    }
}