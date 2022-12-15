package lapr.project.demonstration.CLASS;

import lapr.project.model.DomainClasses.Address;

import java.util.LinkedList;

public class AddressRegister {

    public LinkedList<Address> addresses;

    public AddressRegister(){
        addresses = new LinkedList<>();
    }

    public boolean addAddress(int id, String street, String doorNumber, String city, String country){
        Address address = new Address(id, street, doorNumber, city, country);
        return addresses.add(address);
    }

    public Address getAddressByID(int id){
        for(Address address : addresses)
            if(address.getId() == id)
                return address;

        return null;
    }
}
