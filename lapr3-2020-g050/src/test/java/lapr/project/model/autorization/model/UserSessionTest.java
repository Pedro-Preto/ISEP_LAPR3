package lapr.project.model.autorization.model;

import lapr.project.model.DomainClasses.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    UserSession userSession;
    UserRole userRole;

    @BeforeEach
    void setUp() {
        userSession = new UserSession(new User("name","email","password"));
        userRole = new UserRole(Constants.ROLE_CLIENT);
        userSession.getUser().addRole(userRole);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void doLogout() {
        userSession.doLogout();
        User user = userSession.getUser();
        assertNull(user, "The user should be null after loggin out.");

        userSession.doLogout();
        user = userSession.getUser();
        assertNull(user, "The user should be null because the user is already logged out.");
    }

    @Test
    void isLoggedIn() {
        boolean actual = userSession.isLoggedIn();
        assertTrue(actual,"Should be true because the user is logged in.");

        userSession.doLogout();
        actual = userSession.isLoggedIn();
        assertFalse(actual,"Should be false because the user isn't logged in.");
    }

    @Test
    void getUserEmail() {
        String expected = "email";
        String actual = userSession.getUserEmail();

        assertEquals(expected,actual,"They should be equal");

        userSession.doLogout();
        actual = userSession.getUserEmail();
        assertNull(actual,"The email should be null because the user is logged out.");
    }

    @Test
    void getUserRole() {
        Integer actual = userSession.getUserRole();
        assertEquals(userRole.getId(), actual,"They should be the same!");

        userSession.doLogout();
        actual = userSession.getUserRole();
        assertNull(actual,"The role should be null after loggin out.");
    }
}