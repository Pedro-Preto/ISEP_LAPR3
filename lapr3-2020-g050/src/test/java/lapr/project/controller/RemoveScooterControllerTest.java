package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveScooterControllerTest {

    private RemoveScooterController removeScooterController;
    private AddScooterController addScooterController;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc =new AddPharmacyController();
        apc.addAddress(13443355,"Rua das Olivas", "012", "Lisboa", "Portugal");
        platform.getPharmacyRegister().addPharmacy("111224542333", "Farmácia OMG", 13443355);
    }

    @BeforeEach
    void setUpControllers(){
        addScooterController = new AddScooterController();
        removeScooterController = new RemoveScooterController();
    }

    @Test
    void removeScooter() {
        boolean actual;
        addScooterController.addScooter(13232424,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "111224542333", "11010001001001");
        actual = removeScooterController.removeScooter(13232424);
        assertTrue(actual, "It should be true, the scooter must be removed!");
        addScooterController.addScooter(13232424,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "111224542333", "11010001001001");
        actual = removeScooterController.removeScooter(13232424);
        assertTrue(actual, "It should be true, the scooter must be removed!");
        actual = removeScooterController.removeScooter(13232424);
        assertTrue(actual, "It should be true, the scooter did not exist on the database, so an error cannot be generated while removing it!");
    }

    @AfterAll
    static void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getPharmacyRegister().removePharmacy("111224542333");
        platform.getAddressRegister().removeAddress(13443355);
    }
}
