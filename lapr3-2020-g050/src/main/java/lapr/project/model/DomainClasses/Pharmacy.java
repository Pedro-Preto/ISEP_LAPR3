package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Pharmacy {

    private final String nif;
    private final String designation;
    private final int addressId;

    public Pharmacy(String nif, String designation, int addressId) {
        this.nif = nif;
        this.designation = designation;
        this.addressId = addressId;
    }

    public String getNif() {
        return nif;
    }

    public String getDesignation() {
        return designation;
    }

    public int getAddressId() {
        return addressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pharmacy)) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return addressId == pharmacy.addressId && nif.equals(pharmacy.nif) && designation.equals(pharmacy.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, designation, addressId);
    }

    @Override
    public String toString() {
        return String.format("Phamacy->NIF-%S,Designation:%s,AddressId:%s",nif,designation,addressId);
    }
}

