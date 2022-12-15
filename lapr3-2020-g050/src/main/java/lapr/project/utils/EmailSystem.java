package lapr.project.utils;

import java.io.FileWriter;
import java.io.IOException;

public class EmailSystem {

    /**
     * Hide the implicit constructor.
     */
    private EmailSystem(){}

    public static boolean sendEmail(String emailAddress, String subject, String message)  {

        try {
            FileWriter myWriter;
            myWriter = new FileWriter(subject+"."+"email.txt", true);

            try {
                myWriter.write(String.format("Email Address: %s %n", emailAddress));
                myWriter.write(String.format("Subject: %s %n", subject));
                myWriter.write(message);
            } finally {
                myWriter.write("-------------------------------------\n");
                myWriter.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error sending the email!");
            return false;
        }

        return true;
    }
}
