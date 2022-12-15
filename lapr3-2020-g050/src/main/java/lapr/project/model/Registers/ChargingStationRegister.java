package lapr.project.model.Registers;

import lapr.project.data.ChargingStationDB;
import lapr.project.model.DomainClasses.ChargingStation;

import java.util.List;

public class ChargingStationRegister {

    private final ChargingStationDB chargingStationDB;


    public ChargingStationRegister() {
        this.chargingStationDB = new ChargingStationDB();
    }

    public Boolean addChargingStation(int parkSlotId, double power, double capacity) {
        ChargingStation cs = new ChargingStation(parkSlotId, power, capacity);
        return chargingStationDB.addChargingStation(cs);
    }

    public Boolean removeChargingStation(int parkSlotId) {
        return chargingStationDB.removeChargingStation(parkSlotId);
    }

    public ChargingStation getChargingStation(int id) {
        try {
            chargingStationDB.getChargingStation(id);
            return chargingStationDB.getChargingStation(id);
        } catch(IllegalArgumentException i) {
            return null;
        }
    }

    public List<ChargingStation> getOccupiedChargingStationsOfPark(int parkId) {
        return chargingStationDB.getOccupiedChargingStationsOfPark(parkId);
    }

}
