package lapr.project.model.DomainClasses;

import java.util.Objects;

public class Client {

    private final String nif;
    private final String emailAddress;
    private final String name;
    private final int addressId;

    public Client(String nif, String emailAddress, String name, int addressId) {
        this.nif = nif;
        this.emailAddress = emailAddress;
        this.name = name;
        this.addressId = addressId;
    }

    public String getNif() {
        return nif;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

    public int getAddressId() {
        return addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return addressId == client.addressId && Objects.equals(nif, client.nif) && Objects.equals(emailAddress, client.emailAddress) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, emailAddress, name, addressId);
    }

    @Override
    public String toString() {
        return String.format("Client->NIF:%s,EmailAddress:%s,Name:%s,AddressId:%s",nif,emailAddress,name,addressId);
    }
}
