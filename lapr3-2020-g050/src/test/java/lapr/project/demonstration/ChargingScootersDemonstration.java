package lapr.project.demonstration;

import lapr.project.controller.*;
import lapr.project.model.DomainClasses.Courier;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargingScootersDemonstration {

    private RemoveScooterController removeScooterController;
    private AddScooterController addScooterController;
    private AddScooterParkController addScooterParkController;
    private ScooterNotificationsController scooterNotificationsController;
    private ChargingStationRegister chargingStationRegister;
    private ParkSlotRegister parkSlotRegister;
    private ScooterParkRegister scooterParkRegister;
    private PharmacyRegister pharmacyRegister;
    private AddressRegister addressRegister;
    private CourierRegister courierRegister;
    private Platform platform;

    @BeforeEach
    void setUp() { //run before all the methods of the demonstration, the database is expected to be empty before the demonstration
        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();
        this.courierRegister = platform.getCourierRegister();

        addressRegister.addAddress(21,"Rua Rua", "21", "Cidade", "Portugal");
        pharmacyRegister.addPharmacy("212212212", "Help", 21);

        courierRegister.addCourier("123123123", "name", "email", "91", "123", 80, 20, "212212212");
        courierRegister.addCourier("321321321", "name", "email", "91", "123", 80, 20, "212212212");
        courierRegister.addCourier("123456789", "name", "email", "91", "123", 80, 20, "212212212");
        courierRegister.addCourier("987654321", "name", "email", "91", "123", 80, 20, "212212212");

        addScooterController.addScooter(31,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "212212212", "11010001001002");
        addScooterController.addScooter(32,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "212212212", "11010001001003");
        addScooterController.addScooter(33,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "212212212", "11010001001004");
        addScooterController.addScooter(34,24.0,3.3,4.7,20.0,134.5,4.3,20.0, "212212212", "11010001001005");

        addScooterParkController.addScooterPark(21,"212212212",200.0);

        addScooterParkController.addParkSlot(22);
        addScooterParkController.addParkSlot(23);
        addScooterParkController.addParkSlot(24);
        addScooterParkController.addParkSlot(25);
        addScooterParkController.addParkSlot(26);

        addScooterParkController.addChargingStation(22,0,0);
        addScooterParkController.addChargingStation(23,0,0);
        addScooterParkController.addChargingStation(24,0,0);
    }

    @AfterEach
    void tearDown() { //run after all the methods of this demonstration

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();
        this.courierRegister = platform.getCourierRegister();

        chargingStationRegister.removeChargingStation(22);
        chargingStationRegister.removeChargingStation(23);
        chargingStationRegister.removeChargingStation(24);

        parkSlotRegister.removeParkSlot(22);
        parkSlotRegister.removeParkSlot(23);
        parkSlotRegister.removeParkSlot(24);
        parkSlotRegister.removeParkSlot(25);
        parkSlotRegister.removeParkSlot(26);

        scooterParkRegister.removeScooterPark(21);

        removeScooterController.removeScooter(31);
        removeScooterController.removeScooter(32);
        removeScooterController.removeScooter(33);
        removeScooterController.removeScooter(34);

        courierRegister.removeCourier("123123123");
        courierRegister.removeCourier("321321321");
        courierRegister.removeCourier("123456789");
        courierRegister.removeCourier("987654321");

        pharmacyRegister.removePharmacy("212212212");
        addressRegister.removeAddress(21);
    }

    @Test
    void chargeScooterWithAnEmptyParkDemonstrationDemonstration() { //run first

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int scooterId = 31;
        int parkId = 21;
        int parkSlotId = 22;

        if(!scooterNotificationsController.chargeScooter(scooterId,parkId,parkSlotId, "123123123")){
            System.out.println("It is not possible to charge a Scooter here!");
        } else {
            System.out.printf("Scooter %s is now charging on the Slot %s of the Park %s.\n", scooterId, parkId, parkSlotId);
            //A file is created with the information to read on the C/Assembly script.
        }
    }

    @Test
    void chargeScooterWithOneOtherChargingStationOccupiedDemonstration() { //run second

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int scooterId = 32;
        int parkId = 21;
        int parkSlotId = 24;

        if(!scooterNotificationsController.chargeScooter(scooterId,parkId,parkSlotId, "321321321")){
            System.out.println("It is not possible to charge a Scooter here!");
        } else {
            System.out.printf("Scooter %s is now charging on the Slot %s of the Park %s.\n", scooterId, parkId, parkSlotId);
            //A file is created with the information to read on the C/Assembly script.
        }
    }

    @Test
    void chargeScooterWithTwoOtherChargingStationsOccupiedDemonstration() { //run third

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int scooterId = 33;
        int parkId = 21;
        int parkSlotId = 23;

        if(!scooterNotificationsController.chargeScooter(scooterId,parkId,parkSlotId, "123456789")){
            System.out.println("It is not possible to charge a Scooter here!");
        } else {
            System.out.printf("Scooter %s is now charging on the Slot %s of the Park %s.\n", scooterId, parkId, parkSlotId);
            //A file is created with the information to read on the C/Assembly script.
        }
    }

    @Test
    void parkScooterWithoutChargingDemonstration() { //run fourth

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int scooterId = 34;
        int parkId = 21;
        int parkSlotId = 25;

        if(!scooterNotificationsController.parkScooter(scooterId,parkId,parkSlotId, "123456789")){
            System.out.println("It is not possible to park a Scooter here!");
        } else {
            System.out.printf("Scooter %s is now parked on the Slot %s of the Park %s.\n", scooterId, parkId, parkSlotId);
            //A file is NOT created
        }
    }

    @Test
    void unparkScooterOnChargingStation() { //to demonstrate that you can't

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int parkSlotId = 23;

        if(!scooterNotificationsController.unparkScooter(parkSlotId)){
            System.out.println("It is not possible to unpark a Scooter in the middle of a charging!");
        } else {
            System.out.printf("ParkSlot %s is now empty.\n", parkSlotId);
        }
    }

    @Test
    void unparkScooter() {

        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = platform.getChargingStationRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();

        int parkSlotId = 25;

        if(!scooterNotificationsController.unparkScooter(parkSlotId)){
            System.out.println("It is not possible to unpark a Scooter in the middle of a charging!");
        } else {
            System.out.printf("ParkSlot %s is now empty.\n", parkSlotId);
        }
    }


}
