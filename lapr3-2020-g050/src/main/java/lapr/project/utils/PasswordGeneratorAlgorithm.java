package lapr.project.utils;

import java.security.SecureRandom;

public class PasswordGeneratorAlgorithm {

    public static final Integer PASSWORD_SIZE = 8;

    /**
     * Hide implicit constructor.
     */
    private PasswordGeneratorAlgorithm() {

    }

    private static final SecureRandom random = new SecureRandom();

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_=+-/";

    public static String generatesPassword() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= PASSWORD_SIZE-1; i++) {
            int index = random.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }
}
