package lapr.project.utils;

import java.io.FileWriter;
import java.util.List;

public class PrintFile {

    public static boolean printFile(String fileName, String message)  {

        try {
            FileWriter myWriter;
            myWriter = new FileWriter(fileName, false);

            try {
                myWriter.write(message);
            } finally {
                myWriter.write("\n-------------------------------------\n\n");
                myWriter.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error print message!");
            return false;
        }

        return true;
    }

    public static boolean printFile(String fileName, List<String> messages)  {

        try {
            FileWriter myWriter;
            myWriter = new FileWriter(fileName, true);

            try {
                for (String message: messages){
                    myWriter.write(message);
                    myWriter.write("%n");
                }

            } finally {
                myWriter.write("\n-------------------------------------\n\n");
                myWriter.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error print messages!");
            return false;
        }

        return true;
    }
}
