package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ScooterRegister;

public class CheckScootersBatteryController {

    private final ScooterRegister scooterRegister;

    public CheckScootersBatteryController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterRegister= platform.getScooterRegister();
    }

    public Double checkScootersParkedBatterys(int scooterId, String pharNIF) {

        return scooterRegister.checkScootersBatterys(scooterId,pharNIF);
    }
}
