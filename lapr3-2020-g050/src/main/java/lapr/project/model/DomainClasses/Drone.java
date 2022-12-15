package lapr.project.model.DomainClasses;

import lapr.project.data.DroneDB;

import java.util.Objects;

public class Drone extends Vehicle implements Comparable<Drone> {

    private final double payload;

    public Drone(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload) {
        super(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif);
        this.payload = payload;
    }

    public Drone(){
        super(Constants.DEFAULT_INT_VALUES, Constants.DRONE_BATTERY_SIZE, Constants.DRONE_BATTERY_SIZE,
                Constants.DRONE_BATTERY_EFFICIENCY,Constants.DRONE_BATTERY_SIZE, Constants.DRONE_WEIGHT,
                Constants.DRONE_FRONTAL_AREA,Constants.DRONE_MOTOR_POWER,
                Constants.DEFAULT_STRING_VALUES);
        this.payload = Constants.DEFAULT_INT_VALUES;
    }

    public double getPayload() {
        return payload;
    }

    @Override
    public int compareTo(Drone o) {
        return this.getId() - o.getId();
    }


    @Override
    public String toString() {
        return String.format("Drone->Payload:%s",payload);
    }
}
