package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.DroneRegister;

public class AddDroneController {

    private final DroneRegister droneRegister;

    public AddDroneController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.droneRegister = platform.getDroneRegister();
    }

    public Boolean addDrone(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload) {
        return droneRegister.addDrone(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, payload);
    }

}
