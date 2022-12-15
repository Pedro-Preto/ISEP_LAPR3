package lapr.project.model.DomainClasses;

import java.util.Objects;

public class PharmacyAdministrator{

    private final String emailAddress;
    private final String name;
    private final String pharmacyNif;

    public PharmacyAdministrator(String emailAddress, String name, String pharmacyNif){
        this.emailAddress = emailAddress;
        this.name = name;
        this.pharmacyNif = pharmacyNif;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPharmacyNif() {
        return pharmacyNif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyAdministrator that = (PharmacyAdministrator) o;
        return Objects.equals(name, that.name) && Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailAddress);
    }

    @Override
    public String toString() {
        return String.format("PharmacyAdministrator->EmailAddress:%s,Name:%s,PharmacyNIF:%s",emailAddress,name,pharmacyNif);
    }
}
