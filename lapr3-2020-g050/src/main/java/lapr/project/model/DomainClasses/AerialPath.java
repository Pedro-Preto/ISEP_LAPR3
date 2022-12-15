package lapr.project.model.DomainClasses;

import java.util.Objects;

public class AerialPath extends Path {

    private final Double airDensity;
    private final Double airDragCoefficient;

    public AerialPath(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity, Double airDensity, Double airDragCoefficient) {
        super(geoLocationStart, geoLocationEnd, distance, windDirection, windVelocity);
        this.airDensity = airDensity;
        this.airDragCoefficient = airDragCoefficient;
    }

    public Double getAirDensity() {
        return airDensity;
    }

    public Double getAirDragCoefficient() {
        return airDragCoefficient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AerialPath that = (AerialPath) o;
        return Objects.equals(airDensity, that.airDensity) && Objects.equals(airDragCoefficient, that.airDragCoefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), airDensity, airDragCoefficient);
    }

    @Override
    public String toString() {
        return String.format("AerialPath->airDensity%s,airDragCoefficient%s",airDensity,airDragCoefficient);

    }
}
