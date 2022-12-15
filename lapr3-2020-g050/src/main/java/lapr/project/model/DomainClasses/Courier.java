package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Courier {

    private final String nif;
    private final String name;
    private final String emailAddress;
    private final String cellPhone;
    private final String iban;
    private final double weight;
    private final double payload;
    private final String pharmacyNif;

    public Courier(String nif, String name, String emailAddress, String cellPhone, String iban, double weight, double payload, String pharmacyNif) {
        this.nif = nif;
        this.name = name;
        this.emailAddress = emailAddress;
        this.cellPhone = cellPhone;
        this.iban = iban;
        this.weight = weight;
        this.payload = payload;
        this.pharmacyNif = pharmacyNif;
    }

    public Courier(){
        this.nif = Constants.DEFAULT_STRING_VALUES;
        this.name = Constants.DEFAULT_STRING_VALUES;
        this.emailAddress = Constants.DEFAULT_STRING_VALUES;
        this.cellPhone = Constants.DEFAULT_STRING_VALUES;
        this.iban = Constants.DEFAULT_STRING_VALUES;
        this.weight = Constants.MEDIUM_WEIGHT;
        this.payload = Constants.MAX_PAYLOAD;
        this.pharmacyNif = Constants.DEFAULT_STRING_VALUES;

    }

    public String getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getIban() {
        return iban;
    }

    public double getWeight() {
        return weight;
    }

    public double getPayload() {
        return payload;
    }

    public String getPharmacyNif() {
        return pharmacyNif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(name, courier.name) && Objects.equals(emailAddress, courier.emailAddress) &&
                Objects.equals(cellPhone, courier.cellPhone) && Objects.equals(iban, courier.iban) && Objects.equals(nif, courier.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailAddress, cellPhone, iban, nif);
    }

    @Override
    public String toString() {
        return  String.format("Courier->NIF:%s,Name:%s,EmailAddress:%s,CellPhone:%s,IBAN:%s,Weight:%s,Payload:%s,PharmacyNIF:%s",nif,name,emailAddress,cellPhone,iban,weight,payload,pharmacyNif);
    }
}
