package lapr.project.model.Registers;

import lapr.project.data.AddressDB;
import lapr.project.model.DomainClasses.Address;

public class AddressRegister {

    private final AddressDB addressDB;

    public AddressRegister(){
        addressDB = new AddressDB();
    }

    public boolean addAddress(int id, String street, String doorNumber, String city, String country){
        Address address = new Address(id, street, doorNumber, city, country);
        return addressDB.addAddress(address);
    }

    public boolean removeAddress(int addressId){
        return addressDB.removeAddress(addressId);
    }

    public Address getAddressByID(int id){
        return addressDB.getAddress(id);
    }
}
