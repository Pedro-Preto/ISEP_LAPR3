package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDroneControllerTest {

    private UpdateDroneController updateDroneController;
    private AddDroneController addDroneController;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc = new AddPharmacyController();
        apc.addAddress(864893, "Rua das Oliveiras", "01211", "Lisboa", "Portugal");
        apc.addAddress(6274, "Rua do Dioguetsa ", "8", "Porto Alto", "Portugal");
        platform.getPharmacyRegister().addPharmacy("3478048589", "Farmácia Porto ", 864893);
        platform.getPharmacyRegister().addPharmacy("745443235", "Farmácia Branco", 6274);
        platform.getDroneRegister().addDrone(3456346, 24.0, 3.3, 4.7, 20.0, 134.5, 4.3, 20.0, "3478048589", 2.0);
    }

    @BeforeEach
    void setUpControllers() {
        updateDroneController = new UpdateDroneController();
        addDroneController = new AddDroneController();
    }

    /**
     * Test of updateDrone method, of class UpdateDroneController.
     */
    @Test
    void updateDrone() {
        updateDroneController.updateDrone(3456346, 56.0, 54.6, 54.4, 545.6, 535.5454, 434, 54, "3478048589", 43);
        Boolean s1 = addDroneController.addDrone(3456346, 56.0, 54.6, 54.4, 545.6, 535.5454, 434, 54, "3478048589", 43);
        assertFalse(s1, "Should be false cause the drone already exists");
        updateDroneController.updateDrone(3456346, 100.0, 34, 3.4, 5.6, 64.2, 434, 56, "745443235", 46);
        Boolean s2 = addDroneController.addDrone(3456346, 100.0, 34, 3.4, 5.6, 64.2, 434, 56, "3478048589", 46);
        assertFalse(s2, "Should be false cause the drone already exists");
        updateDroneController.updateDrone(3456346, 132.0, 343, 3.43, 5.32, 64.32, 434, 56, "3478048589", 487);
        Boolean s3 = addDroneController.addDrone(3456346, 132.0, 343, 3.43, 5.32, 64.32, 434, 56, "3478048589", 487);
        assertFalse(s3, "Should be false cause the drone already exists");

    }

    @AfterEach
    void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getDroneRegister().removeDrone(3456346);
        platform.getPharmacyRegister().removePharmacy("745443235");
        platform.getPharmacyRegister().removePharmacy("3478048589");
        platform.getAddressRegister().removeAddress(864893);
        platform.getAddressRegister().removeAddress(6274);

    }

}