package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Scooter extends Vehicle implements Comparable<Scooter> {

    private final String qrCode;

    public Scooter(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery,
                   double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        super(id, batterySize, batteryConsumption, batteryEfficiency, currentBattery, weight, frontalArea, motorPower, pharmacyNif);
        this.qrCode = qrCode;
    }

    /**
     * Empty constructor for scooter, folowing Aether450 model.
     * Note: Considering the frontal area as a rectangle.
     */
    public Scooter(){
        super(Constants.DEFAULT_INT_VALUES, Constants.SCOOTER_BATTERY_SIZE, Constants.SCOOTER_BATTERY_CONSUMPTION,
                Constants.SCOOTER_BATTERY_EFFICIENCY,Constants.SCOOTER_BATTERY_SIZE, Constants.SCOOTER_WEIGHT,
                Constants.SCOOTER_WIDTH*Constants.SCOOTER_HEIGHT,Constants.SCOOTER_MOTOR_POWER,
                Constants.DEFAULT_STRING_VALUES);
        this.qrCode = Constants.DEFAULT_STRING_VALUES;
    }

    public String getQrCode() {
        return qrCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter)) return false;
        Scooter scooter = (Scooter) o;
        return Objects.equals(qrCode, scooter.qrCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qrCode);
    }

    @Override
    public String toString() {
        return String.format("Scooter battery size: %f kWh \n Scooter battery efficiency: %f \nScooter mass: %f kg\nScooter frontal area: %f m^2\n", getBatterySize()
                , getBatteryEfficiency(), getWeight(), getFrontalArea());
    }

    @Override
    public int compareTo(Scooter o) {
        return this.getId() - o.getId();
    }
}
