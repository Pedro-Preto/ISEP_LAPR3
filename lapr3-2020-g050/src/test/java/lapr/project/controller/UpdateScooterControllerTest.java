package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.DomainClasses.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateScooterControllerTest {
    private UpdateScooterController updateScooterController;
    private AddScooterController addScooterController;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc = new AddPharmacyController();
        apc.addAddress(864893, "Rua das Oliveiras", "01211", "Lisboa", "Portugal");
        apc.addAddress(6274, "Rua do Dioguetsa ", "8", "Porto Alto", "Portugal");
        platform.getPharmacyRegister().addPharmacy("3478048589", "Farmácia Porto ", 864893);
        platform.getPharmacyRegister().addPharmacy("745443235", "Farmácia Branco", 6274);
        platform.getScooterRegister().addScooter(3456346, 24.0, 3.3, 4.7, 20.0, 134.5, 4.3, 20.0, "3478048589", "Oi linda");
    }

    @BeforeEach
    void setUpControllers() {
        updateScooterController = new UpdateScooterController();
        addScooterController = new AddScooterController();
    }

    /**
     * Test of updateScooter method, of class UpdateScooterController.
     */
    @Test
    void updateScooter() {
        updateScooterController.updateScooter(3456346, 56.0, 54.6, 54.4, 545.6, 535.5454, 434, 54, "3478048589", "Oi linda");
        Boolean s1 = addScooterController.addScooter(3456346, 56.0, 54.6, 54.4, 545.6, 535.5454, 434, 54, "3478048589", "Oi linda");
        assertFalse(s1, "Should be false cause the scooter already exists");
        updateScooterController.updateScooter(3456346, 100.0, 34, 3.4, 5.6, 64.2, 434, 56, "745443235", "Oi linda");
        Boolean s2 = addScooterController.addScooter(3456346, 100.0, 34, 3.4, 5.6, 64.2, 434, 56, "3478048589", "Oi linda");
        assertFalse(s2, "Should be false cause the scooter already exists");
        updateScooterController.updateScooter(3456346, 132.0, 343, 3.43, 5.32, 64.32, 434, 56, "3478048589", "Oi linda");
        Boolean s3 = addScooterController.addScooter(3456346, 132.0, 343, 3.43, 5.32, 64.32, 434, 56, "3478048589", "Oi linda");
        assertFalse(s3, "Should be false cause the scooter already exists");

    }

    @AfterEach
    void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getScooterRegister().removeScooter(3456346);
        platform.getPharmacyRegister().removePharmacy("745443235");
        platform.getPharmacyRegister().removePharmacy("3478048589");
        platform.getAddressRegister().removeAddress(864893);
        platform.getAddressRegister().removeAddress(6274);

    }


}

