package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDroneControllerTest {
    private ApplicationEM app;
    private Platform platform;
    private RemoveDroneController removeDroneController;
    private AddDroneController addDroneController;


    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController phar = new AddPharmacyController();
        phar.addAddress(116575711, "Rua da Luz", "1", "Lisboa", "Portugal");
        platform.getPharmacyRegister().addPharmacy("20065661", "Farmácia Benfica", 116575711);
    }

    @BeforeEach
    void setUpControllers() {
        addDroneController = new AddDroneController();
        removeDroneController = new RemoveDroneController();
    }

    @Test
    void removeDrone() {
        boolean actual;
        addDroneController.addDrone(7668686,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "20065661", 1.1);
        actual = removeDroneController.removeDrone(7668686);
        assertTrue(actual, "It should be true, the drone must be removed!");
        addDroneController.addDrone(7668686,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "20065661", 1.1);
        actual = removeDroneController.removeDrone(7668686);
        assertTrue(actual, "It should be true, the drone must be removed!");
        actual = removeDroneController.removeDrone(7668686);
        assertTrue(actual, "It should be true, the drone did not exist on the database, so an error cannot be generated while removing it!");

    }

    @AfterAll
    static void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getPharmacyRegister().removePharmacy("20065661");
        platform.getAddressRegister().removeAddress(116575711);
    }
}