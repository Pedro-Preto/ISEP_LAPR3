package lapr.project.model.autorization;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.Constants;
import lapr.project.model.autorization.model.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutorizationFacadeTest {

    AutorizationFacade auth;

    @BeforeEach
    void setUp() {
        auth = ApplicationEM.getInstance().getPlatform().getAutorization();
        auth.registerUserRole(233,Constants.ROLE_ADMINISTRATOR + "1");
        auth.registerUserRole(234,Constants.ROLE_PHARMACY_ADMINISTRATOR + "1");
        auth.registerUserRole(235,Constants.ROLE_CLIENT + "1");
        auth.registerUserRole(236,Constants.ROLE_COURIER + "1");

        auth.registerUserWithRole("Jerónimo","jsm.ad@gmail.com","1234fd", Constants.ROLE_CLIENT);
        auth.registerUserWithRole("Jerónimo1","jsm1.ad@gmail.com","1234fd", Constants.ROLE_COURIER);

    }

    @AfterEach
    void tearDown() {
        auth.deleteUser("jsm.ad@gmail.com");
        auth.deleteUser("jsm1.ad@gmail.com");
        auth.deleteUser("ndn.ad@gmail.com");
        auth.deleteUser("nd@gmail.com");
        auth.deleteUser("joaquim@gmail.com");
        auth.deleteUser("jmd@gmail.com");
        auth.deleteUser("zecamelo@gmail.com");

        auth.deleteUserRoleAndUsers(233);
        auth.deleteUserRoleAndUsers(234);
        auth.deleteUserRoleAndUsers(235);
        auth.deleteUserRoleAndUsers(236);

        auth.deleteUserRole("ROLE");
        auth.deleteUserRole("ROLE 2");
        auth.deleteUserRole("A");
    }

    @Test
    void registerUserRole() {
        System.out.println("Register User Role");

        boolean actual = auth.registerUserRole(1,Constants.ROLE_ADMINISTRATOR);
        assertFalse(actual, "The result should be false because already exists a role named Administrator registered on the system");

        actual = auth.registerUserRole(2,Constants.ROLE_PHARMACY_ADMINISTRATOR);
        assertFalse(actual, "The result should be false because already exists a role named Pharmacy Administrator registered on the system");

        actual = auth.registerUserRole(531,"ROLE");
        assertTrue(actual, "The result should be true because there isn't a role named 'ROLE' registered on the system yet");

        actual = auth.registerUserRole(532,"A");
        assertTrue(actual, "The result should be true because there isn't a role named 'A' registered on the system yet");

        actual = auth.registerUserRole(532,"A");
        assertFalse(actual, "The result should be true because there is already a role named 'A' registered on the system yet");



    }

//    @Test
//    void registerUser() {
//        System.out.println("Register User");
//
//        boolean actual = auth.registerUser("Jerónimo","jsm.ad@gmail.com","1234fd");
//        assertTrue(actual,"The user should be registered sucessfully");
//
//        actual = auth.registerUser("Jerónimo","jsm.ad@gmail.com","1234fd");
//        assertFalse(actual,"The user should'nt be registered , because he is already registered.");
//
//
//    }

    @Test
    void registerUserWithRole() {
        System.out.println("Register User With Role");

        boolean actual = auth.registerUserWithRole("Joaquim","joaquim@gmail.com","1234fd", Constants.ROLE_CLIENT);
        assertTrue(actual,"The user should be registered sucessfully");

        actual = auth.registerUserWithRole("Jerónimo","jsm.ad@gmail.com","1234fd", Constants.ROLE_CLIENT);
        assertFalse(actual,"The user should'nt be registered , because he is already registered.");


    }

    @Test
    void hasRegisteredUser() {
        System.out.println("has Registered User");

        boolean actual = auth.hasRegisteredUser("nd@gmail.com");
        assertFalse(actual,"The user should'nt be found sucessfully");

        auth.registerUserWithRole("Jerónimo","nd@gmail.com","1234fd", Constants.ROLE_CLIENT);

        actual = auth.hasRegisteredUser("nd@gmail.com");
        assertTrue(actual,"The user should be found sucessfully");

    }

//    @Test
//    void doLogin() {
//        System.out.println("has Registered User");
//
//        UserSession userSession = auth.doLogin("", "");
//        assertNull(userSession, "The user session should be null because the login wasn't sucessfull.");
//
//        auth.registerUserWithRole("Jerónimo","jsm.ad@gmail.com","1234fd", Constants.ROLE_CLIENT);
//
//        userSession = auth.doLogin("jsm.ad@gmail.com", "1234fd");
//
//        assertEquals("jsm.ad@gmail.com", userSession.getUserEmail(), "The user sessions should have the same email as the client.");
//        auth.doLogout();
//    }

    @Test
    void getUserSession() {
        System.out.println("get User Session");

        UserSession actual = auth.getUserSession();
        assertNull(actual, "Should be null because there is no login active!");

        auth.registerUserWithRole("Jerónimo Mendes","jmd@gmail.com","1234fd", Constants.ROLE_CLIENT);
        UserSession expected = auth.doLogin("jmd@gmail.com", "1234fd");
        actual = auth.getUserSession();

        assertEquals(expected,actual,"They should be the same!");
        assertEquals("jmd@gmail.com",actual.getUserEmail(),"The user email should be 'jmd@gmail.com'.");
        auth.doLogout();
    }

    @Test
    void doLogout() {
        System.out.println("do Logout");

        auth.doLogout();
        UserSession userSession = auth.getUserSession();
        assertNull(userSession,"The user session should be null.");

        auth.registerUserWithRole("Jerónimo","jsm.ad@gmail.com","1234fd", Constants.ROLE_CLIENT);
        auth.doLogin("jsm.ad@gmail.com", "1234fd");
        auth.doLogout();
        userSession = auth.getUserSession();
        assertNull(userSession,"The user session should be null after loggin out.");

    }

    @Test
    void deleteUserRole() {
        auth.registerUserRole(555,"ROLE");
        auth.registerUserRole(222,"ROLE 2", "Role 2 description");
        auth.registerUserWithRole("Nandinho Adelino","ndn.ad@gmail.com","123114fd", "ROLE");

        boolean actual = auth.deleteUserRole("ROLE");
        assertFalse(actual, "Should be false, because the user role is being used.");

        auth.deleteUser("ndn.ad@gmail.com");
        actual = auth.deleteUserRole("ROLE");
        assertTrue(actual, "Should be true, because the user role is not being used.");

        actual = auth.deleteUserRole("ROLE 2");
        assertTrue(actual, "Should be true, because the user role is not being used.");

        actual = auth.deleteUserRole("ROLE");
        assertFalse(actual, "Should be false, because the user role doesn't exist.");

    }

    @Test
    void deleteUser() {
        System.out.println("delete User ");

        boolean actual = auth.deleteUser("jsm.ad@gmail.com");
        assertTrue(actual, "Should be true because the user is registered.");

        actual = auth.deleteUser("jsm.ad@gmail.com");
        assertFalse(actual, "Should be false because the user isn't registered.");

        actual = auth.deleteUser("jsm1.ad@gmail.com");
        assertTrue(actual, "Should be true because the user is registered.");


    }

    @Test
    void deleteUserRoleAndUsers() {
        auth.registerUserRole(555,"ROLE");
        auth.registerUserRole(222,"ROLE 2", "Role 2 description");
        auth.registerUserWithRole("Nandinho Adelino","ndn.ad@gmail.com","123114fd", "ROLE");
        auth.registerUserWithRole("Zezinho Camelo","zecamelo@gmail.com","123114fd", "ROLE");

        boolean actual = auth.deleteUserRoleAndUsers(555);
        assertTrue(actual,"Should be true.");

        actual = auth.deleteUserRoleAndUsers(222);
        assertTrue(actual,"Should be true.");

        actual = auth.deleteUserRoleAndUsers(-1);
        assertFalse(actual,"Should be false because the role doesn't exist.");

    }
}