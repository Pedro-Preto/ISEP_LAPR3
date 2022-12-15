package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddScooterControllerTest {

    private RemoveScooterController removeScooterController;
    private AddScooterController addScooterController;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc =new AddPharmacyController();
        apc.addAddress(11111,"Rua das Oliveiras", "01211", "Lisboa", "Portugal");
        platform.getPharmacyRegister().addPharmacy("111222333", "Farmácia", 11111);
    }

    @BeforeEach
    void setUpControllers(){
        addScooterController = new AddScooterController();
        removeScooterController = new RemoveScooterController();
    }

    @Test
    void addScooter() {
        boolean actual;
        actual = addScooterController.addScooter(1111,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "111222333", "11010001001001");
        assertTrue(actual, "It should be true, the scooter must be created!");
        actual = addScooterController.addScooter(1111,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "111222333", "11010001001001");
        assertFalse(actual, "It should be false, the scooter already exists on database");
        removeScooterController.removeScooter(1111);
        actual = addScooterController.addScooter(1111,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "111222333", "11010001001001");
        assertTrue(actual, "It should be true, the scooter must be created!");
        removeScooterController.removeScooter(1111);
    }

    @AfterEach
    void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getPharmacyRegister().removePharmacy("111222333");
        platform.getAddressRegister().removeAddress(11111);
        removeScooterController.removeScooter(1111);
    }
}
