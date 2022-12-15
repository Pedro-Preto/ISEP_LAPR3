package lapr.project.model.DomainClasses;

import java.util.Objects;

public class LandPath extends Path {

    private Double roadResistanceCoefficient;
    private Double inclination;

    public LandPath(Integer startGeolocationId, Integer endGeolocationId , Double distance, Double windDirection,
                    Double windVelocity, Double roadResistanceCoefficient, Double inclination) {
        super(startGeolocationId, endGeolocationId, distance, windDirection, windVelocity);
        this.roadResistanceCoefficient = roadResistanceCoefficient;
        this.inclination = inclination;
    }

    public Double getRoadResistanceCoefficient() {
        return roadResistanceCoefficient;
    }

    public Double getInclination() {
        return inclination;
    }

    public void setRoadResistanceCoefficient(Double roadResistanceCoefficient) {
        this.roadResistanceCoefficient = roadResistanceCoefficient;
    }

    public void setInclination(Double inclination) {
        this.inclination = inclination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LandPath landPath = (LandPath) o;
        return Objects.equals(roadResistanceCoefficient, landPath.roadResistanceCoefficient) && Objects.equals(inclination, landPath.inclination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roadResistanceCoefficient, inclination);
    }

    @Override
    public String toString() {
        return String.format("%s\nRoadResistanceCoefficient: %s \nInclination:%s",super.toString(),roadResistanceCoefficient, inclination);
    }
}
