package lapr.project.model.DomainClasses;

import java.util.Objects;

public class GeoLocation {

    private int id;
    private final Double longitude;
    private final Double latitude;
    private final Integer altitude;
    private final int addressID;

    public GeoLocation(int id, Double longitude, Double latitude, Integer altitude, int addressID) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.addressID = addressID;
    }

    public GeoLocation(Double longitude, Double latitude, Integer altitude, int addressID) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.addressID = addressID;
    }

    public int getId() {
        return id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public int getAddressId() {
        return addressID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocation that = (GeoLocation) o;
        return Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, altitude);
    }

    @Override
    public String toString() {
        return String.format("longitude='%f', latitude='%f', altitude='%f",longitude,latitude,altitude);
    }
}
