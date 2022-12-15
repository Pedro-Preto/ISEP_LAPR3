package lapr.project.model.Registers;

import lapr.project.controller.ApplicationEM;
import lapr.project.data.ScooterDB;
import lapr.project.model.DomainClasses.Scooter;

import java.util.List;

public class ScooterRegister {

    private final ScooterDB scooterDB;

    public ScooterRegister() {
        this.scooterDB = new ScooterDB();
    }

    public boolean addScooter(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        Scooter scooter = new Scooter(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, qrCode);
        return scooterDB.addScooter(scooter);
    }

    public boolean removeScooter(int scooterId) {
        return scooterDB.removeScooter(scooterId);
    }

    public Boolean updateScooter(int scooterId, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        removeScooter(scooterId);
        return addScooter(scooterId, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif, qrCode);
    }

    public Scooter getScooter(int scooterId) {
        return scooterDB.getScooter(scooterId);
    }

    public Scooter getScooterByQrCode(String scooterQrCode) {
        return scooterDB.getScooterByQrCode(scooterQrCode);
    }

    public Scooter getBetterScooterToDelivery(String pharmacyNif){
        return scooterDB.getBetterScooterToDelivery(pharmacyNif);
    }


    public Double checkScootersBatterys(int scotterId, String pharNIF) {
        List<Scooter> pharScooters = ApplicationEM.getInstance().getPlatform().getScooterParkRegister().getScootertParkedOnPharmacy(pharNIF);
        double battery = 0;
        for (Scooter sc : pharScooters) {
            int sId = sc.getId();
            if (scotterId == sId) {
                battery = sc.getCurrentBattery();
            }
        }
        return battery;
    }


}
