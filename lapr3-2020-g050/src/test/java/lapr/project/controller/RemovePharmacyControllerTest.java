package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RemovePharmacyControllerTest {

    private RemovePharmacyController removePharmacyController;
    private AddPharmacyController addPharmacyController;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc =new AddPharmacyController();
        apc.addAddress(65575757,"Rua da esquina", "012", "Lisboa", "Portugal");

    }

    @BeforeEach
    void setUpControllers(){
        addPharmacyController = new AddPharmacyController();
        removePharmacyController = new RemovePharmacyController();
    }

    @Test
    void removePharmacy() {
        boolean actual;
        addPharmacyController.addPharmacy("767676711","Farmácia Benfica",65575757);
        actual = removePharmacyController.removePharmacy("767676711");
        assertTrue(actual, "It should be true, the pharmacy must be removed!");
        addPharmacyController.addPharmacy("767676711","Farmácia Benfica",65575757);
        actual = removePharmacyController.removePharmacy("767676711");
        assertTrue(actual, "It should be true, the pharmacy must be removed!");
        actual = removePharmacyController.removePharmacy("767676711");
        assertTrue(actual, "It should be true, the pharmacy did not exist on the database, so an error cannot be generated while removing it!");
    }

    @AfterAll
    static void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getAddressRegister().removeAddress(65575757);
    }
}


