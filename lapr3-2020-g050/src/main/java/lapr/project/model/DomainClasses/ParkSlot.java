package lapr.project.model.DomainClasses;

import java.util.Objects;

public class ParkSlot {

    private final int id;
    private final int parkId;
    private final Integer vehicleId;

    public ParkSlot(int id, int parkId, Integer vehicleId) {
        this.id = id;
        this.parkId = parkId;
        this.vehicleId = vehicleId;
    }

    public int getId() {
        return id;
    }

    public int getParkId() {
        return parkId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkSlot)) return false;
        ParkSlot parkSlot = (ParkSlot) o;
        return id == parkSlot.id && parkId == parkSlot.parkId && vehicleId == parkSlot.vehicleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parkId, vehicleId);
    }

    @Override
    public String toString() {
        return String.format("ParkSlot->Id:%s,ParkId:%s,VehicleId:%s", id, parkId, vehicleId);
    }
}
