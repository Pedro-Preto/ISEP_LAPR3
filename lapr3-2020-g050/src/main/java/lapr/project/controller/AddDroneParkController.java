package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ChargingStationRegister;
import lapr.project.model.Registers.DroneParkRegister;
import lapr.project.model.Registers.ParkSlotRegister;

public class AddDroneParkController {

    private final DroneParkRegister droneParkRegister;
    private final ParkSlotRegister parkSlotRegister;
    private final ChargingStationRegister chargingStationRegister;

    public AddDroneParkController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.droneParkRegister = platform.getDroneParkRegister();
        this.parkSlotRegister = platform.getParkSlotRegister();
        this.chargingStationRegister = platform.getChargingStationRegister();
    }

    public Boolean addDronePark(int id, String pharmacyNif, double power) {
        return droneParkRegister.addDronePark(id, pharmacyNif, power);
    }

    public Boolean addParkSlot(int id, int parkId) {
        Integer a = null;
        return parkSlotRegister.addParkSlot(id, parkId,a);
    }

    public Boolean addChargingStation(int id, double power, double capacity) {
        return chargingStationRegister.addChargingStation(id, power,capacity);
    }

}
