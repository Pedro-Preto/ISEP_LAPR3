package lapr.project.model.DomainClasses;

import java.util.Objects;

public class ChargingStation {


    private final int parkSlotId;
    private final double power;
    private final double capacity;


    public ChargingStation(int parkSlotId, double power, double capacity) {
        this.parkSlotId = parkSlotId;
        this.power = power;
        this.capacity = capacity;
    }

    public int getParkSlotId() {
        return parkSlotId;
    }

    public double getPower() {
        return power;
    }

    public double getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargingStation)) return false;
        ChargingStation that = (ChargingStation) o;
        return parkSlotId == that.parkSlotId && Double.compare(that.power, power) == 0 && Double.compare(that.capacity, capacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkSlotId, power, capacity);
    }

    @Override
    public String toString() {
        return String.format("ChargingStation->ParkSlotId:%s,Power:%s,Capacitys:%s",parkSlotId,power,capacity);
    }
}