package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CheckCreditsControllerTest {

    private RegisterClientController registerClientController;
    private Platform platform;
    AddPharmacyController phar ;
    RegistrationController registrationController;
    private CheckCreditsController checkCreditsController;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        registerClientController = new RegisterClientController();
        registrationController = new RegistrationController();
        phar = new AddPharmacyController();
        checkCreditsController = new CheckCreditsController();
        loginController = new LoginController();

        phar.addAddress(542513, "Rua de Cima", "4", "Aveiro", "Portugal");

        registerClientController.addClient("5462421312345", "ricardo12321@gmail.com", "Ricardo", 542513);
        registerClientController.registerClient("ricardo12321@gmail.com", "rpw");
        loginController.doLogin("ricardo12321@gmail.com", "rpw");
        platform.getCreditsRegister().addCredits(4,24,"5462421312345");
    }

    @AfterEach
    void tearDown() {
        loginController.doLogout();

        platform.getCreditsRegister().removeCredits(4);
        registerClientController.removeClient("5462421312345");
        registrationController.deleteUser("ricardo12321@gmail.com");

        platform.getAddressRegister().removeAddress(542513);
    }

    @Test
    void checkCredits() {


        int expected = 24;
        int result = checkCreditsController.checkCredits();

        assertEquals (expected, result);


    }
}