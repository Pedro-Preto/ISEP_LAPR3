package lapr.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorAlgorithmTest {

    @Test
    void generatesPassword() {
        String password = PasswordGeneratorAlgorithm.generatesPassword();
        assertEquals(PasswordGeneratorAlgorithm.PASSWORD_SIZE, password.length(), "They should be equal.");
    }
}