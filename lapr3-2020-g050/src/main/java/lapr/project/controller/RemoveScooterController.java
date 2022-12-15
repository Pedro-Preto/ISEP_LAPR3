package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ScooterRegister;

public class RemoveScooterController {

    private final ScooterRegister scooterRegister;

    public RemoveScooterController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.scooterRegister = platform.getScooterRegister();
    }

    public boolean removeScooter(int scooterId){
        if(scooterId == 0)
            return false;
        return this.scooterRegister.removeScooter(scooterId);
    }

}
