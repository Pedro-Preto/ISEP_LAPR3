package lapr.project.controller;

import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.ClientRegister;

public class RegisterClientController {

    private final ClientRegister clientRegister;
    private final RegistrationController registrationController;
    private final AddressRegister addressRegister;

    public RegisterClientController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.clientRegister = platform.getClientRegister();
        this.registrationController = new RegistrationController();
        this.addressRegister = platform.getAddressRegister();
    }

    public Boolean addClient(String nif, String emailAddress, String name, int addressId) {
        return clientRegister.addClient(nif, emailAddress, name, addressId);
    }

    public boolean addAddress(int id, String street, String doorNumber, String city, String country){
        return addressRegister.addAddress(id, street, doorNumber, city, country);
    }

    public boolean removeClient(String nif){
        return clientRegister.removeClient(nif);
    }

    public Boolean registerClient(String emailAddress, String pwd) {
        Client client = clientRegister.getClientByEmail(emailAddress);
        return registrationController.registerClient(client, pwd);
    }

}
