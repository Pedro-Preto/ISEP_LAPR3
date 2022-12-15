package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.DroneRegister;

public class RemoveDroneController {
    private final DroneRegister droneRegister;

    public RemoveDroneController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.droneRegister = platform.getDroneRegister();
    }

    public Boolean removeDrone(int droneId) {
        return this.droneRegister.removeDrone(droneId);
    }

}
