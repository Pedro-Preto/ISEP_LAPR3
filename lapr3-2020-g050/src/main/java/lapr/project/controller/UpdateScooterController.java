package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ScooterRegister;

public class UpdateScooterController {


    private final ScooterRegister scooterRegister;

    public UpdateScooterController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterRegister = platform.getScooterRegister();
    }

    public Boolean updateScooter(int scooterId, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        return scooterRegister.updateScooter(scooterId, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, qrCode);
    }
}
