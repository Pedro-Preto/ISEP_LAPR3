package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Address {
    private final int id;
    private final String street;
    private final String doorNumber;
    private final String city;
    private final String country;

    public Address(int id, String street, String doorNumber, String city, String country) {
        this.id = id;
        this.street = street;
        this.doorNumber = doorNumber;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(street, address.street) && Objects.equals(doorNumber, address.doorNumber) && Objects.equals(city, address.city) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, doorNumber, city, country);
    }

    @Override
    public String toString() {
        return String.format("Street: %s | DoorNumber:%s",street, doorNumber);
    }
}
