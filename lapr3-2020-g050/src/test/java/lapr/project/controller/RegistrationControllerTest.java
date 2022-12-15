package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationControllerTest {

    RegistrationController registrationController;
    Platform platform;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        registrationController = new RegistrationController();
    }

    @AfterEach
    void tearDown() {
        registrationController.deleteUser("a@a.a");

        registrationController.deleteUser("joana@courier.pt");
        registrationController.deleteUser("rui@courier.pt");

        registrationController.deleteUser("joana@client.pt");
        registrationController.deleteUser("catia@client.pt");

        platform.getAddressRegister().removeAddress(75675646);

    }


    @Test
    void registerCourier() {
        Courier c1 = new Courier ("587523977","Joana", "joana@courier.pt" ,"923673459","PT500000367223679872 21", 70,15,"111222333");
        Courier c2 = new Courier ("587523977","Joana", "joana@courier.pt" ,"923673459","PT500000367223679872 21", 70,15,"111222333") ;
        Courier c3 = new Courier ("587524777", "Rui", "rui@courier.pt" ,"927673459","PT500000367223679872 38", 70,15,"111222333");

        boolean result1 = registrationController.registerCourier(c1);
        boolean result2 = registrationController.registerCourier(c2);
        boolean result3 = registrationController.registerCourier(c3);

        assertTrue(result1, "Should pass, this courier does not exists");
        assertFalse(result2, "Should fail, this courier already exists");
        assertTrue(result3, "Should pass, this courier does not exists");


    }

    @Test
    void registerClient() {
        platform.getAddressRegister().addAddress(75675646,"Rua 991", "991", "Barcelos", "Portugal");

        Client c1 = new Client ("123456789", "joana@client.pt","joana",75675646);
        Client c2 = new Client ("123456789", "joana@client.pt","joana",75675646);
        Client c3 = new Client ("125556789", "catia@client.pt","catia",75675646);

        boolean result1 = registrationController.registerClient(c1,"aa");
        boolean result2 = registrationController.registerClient(c2, "aa");
        boolean result3 = registrationController.registerClient(c3, "aa");
        assertTrue(result1, "Should pass, this courier does not exists");
        assertFalse(result2, "Should fail, this courier already exists");
        assertTrue(result3, "Should pass, this courier does not exists");



    }

    @Test
    void registerPharmacyAdmin() {


        boolean result =  registrationController.registerPharmacyAdmin(new PharmacyAdministrator("a@a.a","Pedro","111222333"));
        assertTrue(result, "The result should be true. Because there isn't any pharmacy administrator yet!");


        result =  registrationController.registerPharmacyAdmin(new PharmacyAdministrator("a@a.a","Pedro","111222333"));
        assertFalse(result, "The result should be false. Because the user is already registered yet.");

    }
}