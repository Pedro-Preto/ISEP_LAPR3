package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterClientControllerTest {

    private RegisterClientController registerClientController;
    private Platform platform;
    AddPharmacyController phar ;
    RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        registerClientController = new RegisterClientController();
        registrationController = new RegistrationController();
        phar = new AddPharmacyController();

        phar.addAddress(90010, "Rua da Luz", "1", "Lisboa", "Portugal");
    }

    @AfterEach
    void tearDown() {
        registerClientController.removeClient("900110000");
        registrationController.deleteUser("client@gmail.com");
        platform.getAddressRegister().removeAddress(90010);
    }

    @Test
    void addClient() {
        Boolean actual;
        actual = registerClientController.addClient("900110000", "client@gmail.com", "client1", 90010);
        assertTrue(actual);
        actual = registerClientController.addClient("900110000", "clientOther@gmail.com", "client1", 90010);
        assertFalse(actual);
        actual = registerClientController.addClient("900120000", "client@gmail.com", "client1", 90010);
        assertFalse(actual);
    }

    @Test
    void registerClient() {
        platform.getClientRegister().addClient("900110000", "client@gmail.com", "client1", 90010);
        Boolean actual = registerClientController.registerClient("client@gmail.com", "c1pw");
        assertTrue(actual);

    }


}