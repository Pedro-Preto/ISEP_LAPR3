package lapr.project.model.Registers;

import lapr.project.data.ParkSlotDB;
import lapr.project.model.DomainClasses.ParkSlot;

public class ParkSlotRegister {
    private final ParkSlotDB parkSlotDB;

    public ParkSlotRegister() {
        this.parkSlotDB = new ParkSlotDB();
    }

    public Boolean addParkSlot(int id, int parkId, Integer vehicleId) {
        ParkSlot ps = new ParkSlot(id, parkId, vehicleId);
        return parkSlotDB.addParkSlot(ps);
    }

    public ParkSlot getParkSlot(int id) { return parkSlotDB.getParkSlot(id); }

    public Boolean removeParkSlot(int id) {
        return parkSlotDB.removeParkSlot(id);
    }
    
    public Boolean updateParkSlot(int slotID, Integer vehicleId) {
        return parkSlotDB.updateParkSlot(slotID,vehicleId);
    }
}
