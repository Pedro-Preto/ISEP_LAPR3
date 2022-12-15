package lapr.project.model.Registers;

import lapr.project.controller.ApplicationEM;
import lapr.project.data.ScooterParkDB;
import lapr.project.model.DomainClasses.Scooter;
import lapr.project.model.DomainClasses.ScooterPark;
import java.util.List;

public class ScooterParkRegister {
    private final ScooterParkDB scooterParkDB;


    public ScooterParkRegister() {
        this.scooterParkDB = new ScooterParkDB();
    }

    public Boolean addScooterPark(int id, String pharmacyNIF, double power) {
        ScooterPark sp = new ScooterPark(id, pharmacyNIF, power);
        ApplicationEM.getInstance().getPlatform().getScooterRegister().addScooter(0,2.0,2.0,2.0,2.0,2.0,2.0,2.0, pharmacyNIF,"11111111");
        return scooterParkDB.addScooterPark(sp);

    }

    public ScooterPark getScooterPark(int id){
        return scooterParkDB.getScooterPark(id);
    }

    public Boolean removeScooterPark(int id) {
        ApplicationEM.getInstance().getPlatform().getScooterRegister().removeScooter(0);
        return scooterParkDB.removeScooterPark(id);
    }
    public List<Scooter> getScootertParkedOnPharmacy(String pharNIF) {
        return scooterParkDB.getScootertParkedOnPharmacy(pharNIF);
    }
}
