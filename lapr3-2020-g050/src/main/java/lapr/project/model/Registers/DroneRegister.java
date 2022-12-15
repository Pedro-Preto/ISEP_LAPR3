package lapr.project.model.Registers;

import lapr.project.data.DroneDB;
import lapr.project.model.DomainClasses.Drone;

public class DroneRegister {
    private final DroneDB droneDB;

    public DroneRegister() {
        this.droneDB = new DroneDB();
    }

    public boolean addDrone(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload){
        Drone drone = new Drone(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, payload);
        return droneDB.addDrone(drone);
    }

    public boolean removeDrone(int droneId) {
        return droneDB.removeDrone(droneId);
    }

    public Boolean updateDrone(int droneId, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload) {
        removeDrone(droneId);
        return addDrone(droneId, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, payload);
    }

}
