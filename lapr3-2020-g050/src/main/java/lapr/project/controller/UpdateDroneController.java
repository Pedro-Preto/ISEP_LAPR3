package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.DroneRegister;

public class UpdateDroneController {

    private final DroneRegister droneRegister;

    public UpdateDroneController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.droneRegister = platform.getDroneRegister();
    }

    public Boolean updateDrone(int scooterId, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload) {
        return droneRegister.updateDrone(scooterId, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, payload);
    }
}
