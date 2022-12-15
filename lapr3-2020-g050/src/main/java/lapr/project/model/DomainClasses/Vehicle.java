package lapr.project.model.DomainClasses;

public abstract class Vehicle {

    private final int id;
    private final double batterySize;
    private final double batteryConsumption;
    private final double batteryEfficiency;
    private final double currentBattery;
    private final double weight;
    private final double frontalArea;
    private final double motorPower;
    private final String pharmacyNif;

    public Vehicle(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif) {
        this.id = id;
        this.batterySize = batterySize;
        this.batteryConsumption = batteryConsumption;
        this.batteryEfficiency = batteryEfficiency;
        this.currentBattery = currentBattery;
        this.weight = weight;
        this.frontalArea = frontalArea;
        this.motorPower = motorPower;
        this.pharmacyNif = pharmacyNif;
    }

    public int getId() {
        return id;
    }

    public double getBatterySize() {
        return batterySize;
    }

    public double getBatteryConsumption() {
        return batteryConsumption;
    }

    public double getBatteryEfficiency() {
        return batteryEfficiency;
    }

    public double getCurrentBattery() {
        return currentBattery;
    }

    public double getWeight() {
        return weight;
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public double getMotorPower() {
        return motorPower;
    }

    public String getPharmacyNif() {
        return pharmacyNif;
    }


    @Override
    public String toString() {
        return String.format("Vehicle->Id:%s,BatterySize:%s,BatteryConsumption:%s,BatteryEfficiency:%s,CurrentBattery:%s,Weight:%s,FrontalArea:%s,MotorPower:%s,PharmacyNif:%s", id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower,pharmacyNif);
    }
}
