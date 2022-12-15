package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.*;

public class AddScooterController {

    private final ScooterRegister scooterRegister;

    public AddScooterController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterRegister = platform.getScooterRegister();
    }

    public Boolean addScooter(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        return scooterRegister.addScooter(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, qrCode);
    }

}

