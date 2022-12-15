package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.*;

public class AddScooterParkController {

    private final ScooterParkRegister scooterParkRegister;
    private final ParkSlotRegister parkSlotRegister;
    private final ChargingStationRegister chargingStationRegister;
    private int parkId;

    public AddScooterParkController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterParkRegister = platform.getScooterParkRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.chargingStationRegister = platform.getChargingStationRegister();
    }

    public Boolean addScooterPark(int id, String pharmacyNif, double power) {
        this.parkId = id;
        return scooterParkRegister.addScooterPark(id, pharmacyNif, power);
    }

    public Boolean addParkSlot(int id) {
        Integer a = null;
        return parkSlotRegister.addParkSlot(id, this.parkId,a);
    }

    public Boolean addChargingStation(int id, double power, double capacity) {
        return chargingStationRegister.addChargingStation(id, power, capacity);
    }
}
