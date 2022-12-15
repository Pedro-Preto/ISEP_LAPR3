//package lapr.project.model.autorization.model;
//
//import lapr.project.controller.ApplicationEM;
//import lapr.project.data.DataHandler;
//import lapr.project.model.DomainClasses.Constants;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserRoleRegisterTest {
//
//    UserRoleRegister userRoleRegister;
//
//    @BeforeEach
//    void setUp() {
//        userRoleRegister = new UserRoleRegister();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void newUserRole() {
//        UserRole expected = new UserRole(51,Constants.ROLE_CLIENT + "1", "Client");
//        UserRole actual = userRoleRegister.newUserRole(51,Constants.ROLE_CLIENT + "1", "Client");
//
//        assertEquals(expected, actual, "They should be equal.");
//
//        expected = new UserRole(Constants.ROLE_ADMINISTRATOR);
//        actual = userRoleRegister.newUserRole(Constants.ROLE_ADMINISTRATOR);
//        assertEquals(expected, actual, "They should be equal.");
//
//
//    }
//
//    @Test
//    void addRole() {
//        boolean actual = userRoleRegister.addRole(null);
//        assertFalse(actual, "Should be false because the role is null.");
//
//        UserRole userRole = userRoleRegister.newUserRole(51,Constants.ROLE_CLIENT + "2", "Client");
//
//        actual = userRoleRegister.addRole(userRole);
//        assertTrue(actual,"Should be true because the role is valid.");
//
//        actual = userRoleRegister.addRole(userRole);
//        assertFalse(actual,"Should be false because the role is already registered on the system.");
//
//        userRoleRegister.deleteUserRole(51);
//    }
//
//    @Test
//    void searchRole() {
//        UserRole actual = userRoleRegister.searchRole(null);
//        assertNull(actual,"Should be null because the role is null.");
//
//        actual = userRoleRegister.searchRole(Constants.ROLE_ADMINISTRATOR + "1123");
//        assertNull(actual,"Should be null because the role doesn't exist.");
//
//        UserRole expected = userRoleRegister.newUserRole(Constants.ROLE_ADMINISTRATOR + "1231");
//        userRoleRegister.addRole(expected);
//        actual = userRoleRegister.searchRole(Constants.ROLE_ADMINISTRATOR);
//        assertEquals(expected,actual,"They should be equal.");
//
//    }
//}