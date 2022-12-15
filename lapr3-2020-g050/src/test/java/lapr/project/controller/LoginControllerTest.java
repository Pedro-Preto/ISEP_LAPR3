package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.model.DomainClasses.*;
import lapr.project.model.autorization.model.UserRole;
import lapr.project.model.autorization.model.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    LoginController loginController;
    RegistrationController registrationController;
    RegisterClientController registerClientController;
    AddPharmacyController addPharmacyController;
    RemovePharmacyController removePharmacyController;
    AddressDB addressDB;
    RegisterCourierController registerCourierController;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
        registrationController = new RegistrationController();
        registerClientController = new RegisterClientController();
        addPharmacyController = new AddPharmacyController();
        removePharmacyController = new RemovePharmacyController();
        registerCourierController = new RegisterCourierController();
        addressDB = new AddressDB();

        Address a1 = new Address(1111, "Rua do Povo", "01","Porto", "Portugal");
        addressDB.addAddress(a1);
        String clientPwd = "sadasd";
        registerClientController.addClient("243251112", "ja@email.com","Joaquina Damasco",1111);
        registrationController.registerClient(new Client("243251112", "ja@email.com","Joaquina Damasco",1111),clientPwd);

        addPharmacyController.addAddress(2222,"Rua 2222","2222","City 22","Country 22");
        addPharmacyController.addPharmacy("18796768","pharmacy",2222);

        registerClientController.addClient("clienttest", "clienttest","clienttest",1111);
        registrationController.registerClient(new Client("clienttest", "clienttest","clienttest",1111),"clienttest");

        addPharmacyController.addPharmacyAdministrator("pharmacyadmintest","pharmacyadmintest","18796768");
        registrationController.registerPharmacyAdmin(new PharmacyAdministrator("pharmacyadmintest","pharmacyadmintest","18796768"),"pharmacyadmintest");

        registerCourierController.addCourier("couriertest","couriertest", "couriertest",
                "21","pt12321312",123.1,0.0,"18796768");
        registrationController.registerCourier(new Courier("couriertest","couriertest", "couriertest",
                "21","pt12321312",123.1,0.0,"18796768"),"couriertest");

    }

    @AfterEach
    void tearDown() {
        registerClientController.removeClient("243251112");
        registrationController.deleteUser("ja@email.com");

        registerClientController.removeClient("clienttest");
        registrationController.deleteUser("clienttest");

        removePharmacyController.removePharmacyAdministrator("pharmacyadmintest");
        registrationController.deleteUser("pharmacyadmintest");

        registerCourierController.removeCourier("couriertest");
        registrationController.deleteUser("couriertest");

        removePharmacyController.removePharmacy("18796768");

        addressDB.removeAddress(1111);
        addressDB.removeAddress(2222);
    }

    @Test
    void doLogin() {
        String strId = "couriertest";
        String pwd = "couriertest";
        boolean result = loginController.doLogin(strId,pwd);
        assertNotNull(ApplicationEM.getInstance().getUserSession(), "The session shouldn't be null!");
        assertTrue(result, "Should be true, because the credentials match");
        loginController.doLogout();

        result = loginController.doLogin("a",pwd);
        assertFalse(result,"Should be false, because the user doesn't exist.");
        assertNull(ApplicationEM.getInstance().getUserSession(), "The session should be null!");
        loginController.doLogout();

        String clientEmail = "ja@email.com";
        String clientPwd = "sadasd";
        result = loginController.doLogin(clientEmail,clientPwd);
        assertNotNull(ApplicationEM.getInstance().getUserSession(), "The session shouldn't be null!");
        assertTrue(result, "The credentials should match.");
        loginController.doLogout();
    }

    @Test
    void getUserRole() {

        String strId = "pharmacyadmintest";
        String pwd = "pharmacyadmintest";
        UserRole expected = new UserRole(2, Constants.ROLE_PHARMACY_ADMINISTRATOR, "Pharmacy Administrator");
        loginController.doLogin(strId,pwd);
        UserRole actual = loginController.getUserRole();
        assertEquals(expected, actual, "The user role should be the same = Administrator");
        loginController.doLogout();

        strId = "couriertest";
        pwd = "couriertest";
        expected = new UserRole(4, Constants.ROLE_COURIER, "Courier");
        loginController.doLogin(strId,pwd);
        actual = loginController.getUserRole();
        assertEquals(expected, actual, "The user role should be the same = Administrator");
        loginController.doLogout();

        strId = "clienttest";
        pwd = "clienttest";
        expected = new UserRole(3, Constants.ROLE_CLIENT,"Client");
        loginController.doLogin(strId,pwd);
        actual = loginController.getUserRole();
        assertEquals(expected, actual, "The user role should be the same = Administrator");
        loginController.doLogout();

    }

    @Test
    void doLogout() {
        String strId = "admin";
        String pwd = "admin";
        loginController.doLogin(strId,pwd);
        loginController.doLogout();

        UserSession userSession = ApplicationEM.getInstance().getUserSession();
        assertNull(userSession, "Should be null because the user logged out.");

        loginController.doLogin("a",pwd);
        loginController.doLogout();

        userSession = ApplicationEM.getInstance().getUserSession();
        assertNull(userSession, "Should be null because the user never logged in.");

        String clientEmail = "ja@email.com";
        String clientPwd = "sadasd";
        loginController.doLogin(clientEmail,clientPwd);
        loginController.doLogout();
        userSession = ApplicationEM.getInstance().getUserSession();
        assertNull(userSession, "Should be null because the user logged out.");

    }
}