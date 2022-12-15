package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailSystemTest {

    @Test
    void sendEmail() {

        boolean result = EmailSystem.sendEmail("email","subject","");
        assertTrue(result, "The email should be sent.");

        result = EmailSystem.sendEmail(null,null,null);
        assertFalse(result,"The email shouldn't be sent!");

    }
}