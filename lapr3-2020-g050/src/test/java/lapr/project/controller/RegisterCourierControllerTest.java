package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCourierControllerTest {

    Platform platform;
    RegisterCourierController registerCourierController;
    RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        ApplicationEM app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        platform.resetData();
        registerCourierController = new RegisterCourierController();
        registrationController = new RegistrationController();

        tearDown();

        platform.getAddressRegister().addAddress(90005,"Rua da Farmácia","213","Porto","Portugal");
        platform.getPharmacyRegister().addPharmacy("900070000","Pharmacy",90005);

        platform.getAddressRegister().addAddress(90006,"Rua da Nilo","213","Porto","Portugal");
        platform.getPharmacyRegister().addPharmacy("900080000","Pharmacy",90006);
    }

    @AfterEach
    void tearDown() {

        platform.getCourierRegister().removeCourier("900080000");
        registrationController.deleteUser("alfredo.batata@lapr.pt");

        platform.getCourierRegister().removeCourier("900090000");
        registrationController.deleteUser("alfredo.batata@lapr3.pt");

        platform.getPharmacyRegister().removePharmacy("900070000");
        platform.getPharmacyRegister().removePharmacy("900080000");

        platform.getAddressRegister().removeAddress(90005);
        platform.getAddressRegister().removeAddress(90005);
        platform.getAddressRegister().removeAddress(90006);
        platform.getAddressRegister().removeAddress(90006);
    }

    @Test
    void addCourier() {
        boolean actual = registerCourierController.addCourier("900080000", "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900070000");
        assertTrue(actual, "Should be true because there isn't any courier on this pharmacy yet!");

        actual = registerCourierController.addCourier("900080000", "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900070000");
        assertFalse(actual, "Should be false bacause there is already a courier with this data on this pharmacy.");

        actual = registerCourierController.addCourier("900080000", "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900080000");
        assertFalse(actual, "Should be false bacause there is already a courier with this data on the system.");

        actual = registerCourierController.addCourier("900090000", "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900080000");
        assertFalse(actual, "Should be false because there is already a courier with this email on the system!");

        actual = registerCourierController.addCourier("900090000", "Alfredo Batata", "alfredo.batata@lapr3.pt","91232121","PT5012321312312312",77.6,0.0,"900080000");
        assertTrue(actual, "Should be true because there isn't any courier on this pharmacy yet!");

        actual = registerCourierController.addCourier(null, "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900080000");
        assertFalse(actual, "Should be false because the nif is null!");
    }

    @Test
    void registerCourier() {
        registerCourierController.addCourier("900080000", "Alfredo Batata", "alfredo.batata@lapr.pt","91232141","PT5012321312312312",77.4,0.0,"900070000");
        registerCourierController.addCourier("900090000", "Alfredo Batata", "alfredo.batata@lapr3.pt","91232142","PT5012321312312313",77.6,0.0,"900080000");

        boolean actual = registerCourierController.registerCourier("900080000");
        assertTrue(actual, "Should be true because there isn't any user registered with this credentials.");

        actual = registerCourierController.registerCourier("900080000");
        assertFalse(actual, "Should be false because there is already an user registered with those credentials.");

        actual = registerCourierController.registerCourier("900090000");
        assertTrue(actual, "Should be true because there isn't any user registered with this credentials.");

        boolean thrown = false;
        try{
            registerCourierController.registerCourier(null);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            thrown = true;
        }
        assertTrue(thrown,"Should throw 'Illegal Argument Exception' because the input is null.");

    }
}