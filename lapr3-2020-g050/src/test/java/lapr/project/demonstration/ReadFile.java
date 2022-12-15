package lapr.project.demonstration;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.Address;
import lapr.project.model.DomainClasses.AerialPath;
import lapr.project.model.DomainClasses.GeoLocation;
import lapr.project.model.DomainClasses.LandPath;
import lapr.project.model.Registers.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadFile {

    final String ADDRESS_FILE = "Files//Address.csv";
    final String PHARMACIES_FILE = "Files//Pharmacies.csv";
    final String CLIENT_FILE = "Files//Clients.csv";
    final String GEOLOCATION_FILE = "Files//Geolocation.csv";
    final String LANDPATH_FILE = "Files//Paths-Land.csv";
    final String AERIALPATH_FILE = "Files//Paths-Air.csv";

    public LinkedList<AerialPath> aerialPaths = new LinkedList<>();
    public LinkedList<LandPath> landPaths = new LinkedList<>();

    public lapr.project.demonstration.CLASS.AddressRegister addressRegister = new lapr.project.demonstration.CLASS.AddressRegister();
    public lapr.project.demonstration.CLASS.GeoLocationRegister geoLocationRegister = new lapr.project.demonstration.CLASS.GeoLocationRegister();

    public boolean readClientsFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((CLIENT_FILE)));
            sc.nextLine();
            ClientRegister clientRegister = ApplicationEM.getInstance().getPlatform().getClientRegister();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //initializes registers

                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                clientRegister.addClient(lineContent[0], lineContent[1], lineContent[2], Integer.parseInt(lineContent[3]));

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean readPharmaciesFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((PHARMACIES_FILE)));
            PharmacyRegister pharmacyRegister = new PharmacyRegister();

            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //initializes registers

                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                pharmacyRegister.addPharmacy(lineContent[0], lineContent[1], Integer.parseInt(lineContent[2]));

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean readGeoLocationFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((GEOLOCATION_FILE)));
            GeoLocationRegister geoLocationRegister = ApplicationEM.getInstance().getPlatform().getGeoLocationRegister();

            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //initializes registers

                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                geoLocationRegister.addGeoLocation(Integer.parseInt(lineContent[0]), Double.parseDouble(lineContent[1]),
                        Double.parseDouble(lineContent[2]), Integer.parseInt(lineContent[3]), Integer.parseInt(lineContent[4]));

                this.geoLocationRegister.addGeoLocation(Integer.parseInt(lineContent[0]), Double.parseDouble(lineContent[1]),
                        Double.parseDouble(lineContent[2]), Integer.parseInt(lineContent[3]), Integer.parseInt(lineContent[4]));

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean readAddressFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((ADDRESS_FILE)));
            sc.nextLine();
            AddressRegister addressRegister = ApplicationEM.getInstance().getPlatform().getAddressRegister();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //initializes registers

                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                addressRegister.addAddress(Integer.parseInt(lineContent[0]), lineContent[1], lineContent[2], lineContent[3], lineContent[4]);

                this.addressRegister.addAddress(Integer.parseInt(lineContent[0]), lineContent[1], lineContent[2], lineContent[3], lineContent[4]);

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean readLandPathFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((LANDPATH_FILE)));
            LandPathRegister landPathRegister = ApplicationEM.getInstance().getPlatform().getLandPathRegister();

            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //initializes registers

                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                //landPathRegister.addLandPath(Integer.parseInt(lineContent[0]), Integer.parseInt(lineContent[1]), Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3]), Double.parseDouble(lineContent[4]), Double.parseDouble(lineContent[5]), Double.parseDouble(lineContent[6]));

                landPaths.add(new LandPath(Integer.parseInt(lineContent[0]), Integer.parseInt(lineContent[1]), Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3]), Double.parseDouble(lineContent[4]), Double.parseDouble(lineContent[5]), Double.parseDouble(lineContent[6])));

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean readAirPathFile() {
        try {
            String[] lineContent = null;
            Scanner sc = new Scanner(new FileReader((AERIALPATH_FILE)));
            sc.nextLine();
            AerialPathRegister aerialPathRegister = ApplicationEM.getInstance().getPlatform().getAerialPathRegister();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //initializes registers



                if (line != null)
                    lineContent = line.split(","); // String[] lineContent is a unidimensional array that contains information about the read line
                //aerialPathRegister.addAerialPath(Integer.parseInt(lineContent[0]), Integer.parseInt(lineContent[1]), Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3]), Double.parseDouble(lineContent[4]), Double.parseDouble(lineContent[5]), Double.parseDouble(lineContent[6]));

                aerialPaths.add(new AerialPath(Integer.parseInt(lineContent[0]), Integer.parseInt(lineContent[1]), Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3]), Double.parseDouble(lineContent[4]), Double.parseDouble(lineContent[5]), Double.parseDouble(lineContent[6])));

            }
            return true; //if file is read correctly
        }
        //catches and treats FileNotFoundException if filename is incorrect
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}