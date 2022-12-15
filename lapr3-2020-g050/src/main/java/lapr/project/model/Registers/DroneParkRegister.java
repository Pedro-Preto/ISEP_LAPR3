package lapr.project.model.Registers;

import lapr.project.data.DroneParkDB;
import lapr.project.model.DomainClasses.DronePark;

public class DroneParkRegister {
    private final DroneParkDB droneParkDB;

    public DroneParkRegister() {
        this.droneParkDB = new DroneParkDB();
    }

    public Boolean addDronePark(int id, String pharmacyNIF, double power) {
        DronePark dp = new DronePark(id, pharmacyNIF, power);
        return droneParkDB.addDronePark(dp);
    }

    public Boolean removeDronePark(int id) {
        return droneParkDB.removeDronePark(id);
    }
}
