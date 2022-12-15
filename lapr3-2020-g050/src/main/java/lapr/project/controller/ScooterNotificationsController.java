package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.*;
import lapr.project.utils.EmailSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ScooterNotificationsController {

    ScooterRegister scooterRegister;
    ScooterParkRegister scooterParkRegister;
    ParkSlotRegister parkSlotRegister;
    ChargingStationRegister chargingStationRegister;

    public ScooterNotificationsController(){
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterRegister = platform.getScooterRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.chargingStationRegister = platform.getChargingStationRegister();
    }

    public Boolean unparkScooter(int parkSlotId){
        ParkSlot parkSlot = parkSlotRegister.getParkSlot(parkSlotId);
        if (chargingStationRegister.getChargingStation(parkSlot.getId()) == null) //checks if its a charging station
            return parkSlotRegister.updateParkSlot(parkSlotId, 0); //returns true if scooter was unparked successfully
        return false; //cant unpark a charging scooter
    }

    public Boolean parkScooter(int scooterId, int parkId, int parkSlotId, String courierNif){
        ParkSlot parkSlot = parkSlotRegister.getParkSlot(parkSlotId);
        if (parkSlot.getVehicleId().intValue() != 0)
            return false;
        if (chargingStationRegister.getChargingStation(parkSlot.getId()) == null)
            return parkSlotRegister.updateParkSlot(parkSlotId, scooterId);
        return chargeScooter(scooterId, parkId, parkSlotId, courierNif);
    }

    public Boolean chargeScooter(int scooterId, int parkId, int parkSlotId, String courierNif) {
        Scooter scooter = scooterRegister.getScooter(scooterId);
        ParkSlot parkSlot = parkSlotRegister.getParkSlot(parkSlotId);
        FileWriter myWriter;
        FileWriter otherWriter;
        String fileText;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String date;
        if (parkSlot.getVehicleId() != 0 || chargingStationRegister.getChargingStation(parkSlot.getId()) == null)
            return false; //park is occupied or park doesnt have a charging station
        chargingStationRegister.removeChargingStation(parkSlotId);
        if (!parkSlotRegister.updateParkSlot(parkSlotId, scooterId)) {
            chargingStationRegister.addChargingStation(parkSlotId, 0, 0);
            return false; //there was an error updating the parkSlot
        }
        chargingStationRegister.addChargingStation(parkSlotId, 0, 0);

        date = dtf.format(now);
        try {
            otherWriter = new FileWriter(new File("scooterData/lock_" + date + ".data.flag"),false);
            myWriter = new FileWriter(new File("scooterData/lock_" + date + ".data"), false);
            try {
                fileText = ((int) scooter.getBatterySize())+ "\n" + ((int) scooter.getCurrentBattery()) + "\n" +
                        ((int) scooter.getBatteryEfficiency()) + "\n" + scooter.getId() + "\n"
                        + parkSlotId + "\n" + courierNif;
                myWriter.write(fileText);
            } finally {
                myWriter.close();
                otherWriter.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error creating the file!");
            return false;
        }
        return true;
    }

    public static void sendChargingTimeEmail() {

        String pathName = "src_others/";
        String[] filenames;
        File filepath = new File(pathName);
        filenames = filepath.list();

        if(filenames != null){
            for(String fileName : filenames){

                if (fileName.startsWith("estimate") && fileName.endsWith(".flag")) {
                    String[] lineContent;
                    int scooterID = 0, parkSlotID = 0, seconds = 0, hours = 0, minutes = 0;
                    String courierNif = "";
                    File file = new File(pathName + fileName.substring(0, 33));
                    try (Scanner read = new Scanner(file)) {


                        read.nextLine();
                        read.nextLine();
                        read.nextLine();
                        read.nextLine();
                        read.nextLine();
                        read.nextLine();
                        read.nextLine();
                        read.nextLine(); //advances # started lines
                        lineContent = read.nextLine().split(",");
                        scooterID = Integer.parseInt(lineContent[0]);
                        parkSlotID = Integer.parseInt(lineContent[1]);
                        courierNif = lineContent[2];
                        read.nextLine();
                        read.nextLine();
                        lineContent = read.nextLine().split(",");
                        hours = Integer.parseInt(lineContent[0]);
                        minutes = Integer.parseInt(lineContent[1]);
                        seconds = Integer.parseInt(lineContent[2]);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                    CourierRegister courierRegister = new CourierRegister();
                    String email = courierRegister.getCourier(courierNif).getEmailAddress();

                    EmailSystem.sendEmail(email, "Estimated vehicle charging time", "The vehicle with ID " + scooterID + " is correctly locked in park slot with ID " + parkSlotID
                            + "\n\nEstimated charging time\n" + hours + "h " + minutes + "m " + seconds + "s\n");

                    System.out.println("\n-------------------\nNOTIFICATION SYSTEM: EMAIL SENT TO " + email);

                    File flagFile = new File(pathName+fileName);
                    System.out.println(fileName);
                    if (file.delete() && flagFile.delete())
                        System.out.println("DELETED FILE! \n-------------------\n");

            }
        }


        }
    }
}
