package lapr.project.controller;

import lapr.project.model.DomainClasses.Park;
import lapr.project.model.DomainClasses.ParkSlot;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ChargingStationRegister;
import lapr.project.model.Registers.CourierRegister;
import lapr.project.model.Registers.ParkSlotRegister;
import lapr.project.model.Registers.ScooterParkRegister;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ScooterNotificationsControllerTest {

    private RemoveScooterController removeScooterController;
    private AddScooterController addScooterController;
    private AddScooterParkController addScooterParkController;
    private ScooterNotificationsController scooterNotificationsController;
    private ChargingStationRegister chargingStationRegister;
    private ParkSlotRegister parkSlotRegister;
    private ScooterParkRegister scooterParkRegister;
    private CourierRegister courierRegister;
    private Platform platform;

    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc =new AddPharmacyController();
        apc.addAddress(21,"Rua Rua", "21", "Cidade", "Portugal");
        platform.getPharmacyRegister().addPharmacy("212212212", "Help", 21);
    }

    @BeforeEach
    void setUpControllers() {
        this.platform = ApplicationEM.getInstance().getPlatform();
        this.addScooterController = new AddScooterController();
        this.removeScooterController = new RemoveScooterController();
        this.addScooterParkController = new AddScooterParkController();
        this.scooterNotificationsController = new ScooterNotificationsController();
        this.chargingStationRegister = new ChargingStationRegister();
        this.parkSlotRegister = new ParkSlotRegister();
        this.scooterParkRegister = new ScooterParkRegister();
        this.courierRegister = platform.getCourierRegister();
    }

    @AfterAll
    static void tearDown() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        platform.getPharmacyRegister().removePharmacy("212212212");
        platform.getAddressRegister().removeAddress(21);
    }

    @AfterEach
    void tearDownDB() {
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
    }

    @Test
    void chargeScooter() {

        boolean actual;
        int expectedId;
        int actualId;
        File file;

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

        actual = scooterNotificationsController.parkScooter(31,21,22, "123123123"); //correct
        assertTrue(actual);
        //file = new File("scooterData/scooter31.txt");
        //assertTrue(file.exists());
        expectedId = 31;
        actualId = parkSlotRegister.getParkSlot(22).getVehicleId();
        assertEquals(expectedId, actualId);

        actual = scooterNotificationsController.parkScooter(32,21,24, "321321321"); //correct
        assertTrue(actual);
        //file = new File("scooterData/scooter32.txt");
        //assertTrue(file.exists());
        expectedId = 32;
        actualId = parkSlotRegister.getParkSlot(24).getVehicleId();
        assertEquals(expectedId, actualId);

        actual = scooterNotificationsController.chargeScooter(33,21,26, "123456789"); //doesnt have a charging station
        assertFalse(actual);
        //file = new File("scooterData/scooter33.txt");
        //assertFalse(file.exists());
        expectedId = 0;
        actualId = parkSlotRegister.getParkSlot(26).getVehicleId();
        assertEquals(expectedId, actualId);

        actual = scooterNotificationsController.parkScooter(34,21,24, "987654321"); //is occupied
        assertFalse(actual);
        expectedId = 32;
        actualId = parkSlotRegister.getParkSlot(24).getVehicleId();
        assertEquals(expectedId, actualId);
    }
}