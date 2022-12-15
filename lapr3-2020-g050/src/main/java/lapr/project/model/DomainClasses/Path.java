package lapr.project.model.DomainClasses;

import java.util.Objects;

public abstract class Path {

    private final int geoLocationStart;
    private final int geoLocationEnd;
    private Double distance;
    private Double windDirection;
    private Double windVelocity;

    public Path(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity) {
        this.geoLocationStart = geoLocationStart;
        this.geoLocationEnd = geoLocationEnd;
        this.distance = distance;
        this.windDirection = windDirection;
        this.windVelocity = windVelocity;
    }

    public int getGeoLocationStart() {
        return geoLocationStart;
    }

    public int getGeoLocationEnd() {
        return geoLocationEnd;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public Double getWindVelocity() {
        return windVelocity;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public void setWindVelocity(Double windVelocity) {
        this.windVelocity = windVelocity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(distance, path.distance) && Objects.equals(windDirection, path.windDirection) && Objects.equals(windVelocity, path.windVelocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, windDirection, windVelocity);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s :\n Distance: %f \n Wind Velocity: %.3f\n Wind Direction Angle: %.1f º\n",
                geoLocationStart, geoLocationEnd, distance, windVelocity, windDirection);
    }
}
