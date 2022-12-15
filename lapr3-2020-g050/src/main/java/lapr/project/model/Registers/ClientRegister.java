package lapr.project.model.Registers;

import lapr.project.data.ClientDB;
import lapr.project.model.DomainClasses.Client;

public class ClientRegister {

    private final ClientDB clientDB;

    public ClientRegister() {
        this.clientDB = new ClientDB();
    }

    public Client getClient(String nif){
        return clientDB.getClient(nif);
    }

    public boolean addClient(String nif, String emailAddress, String name, int addressId) {
        Client client = new Client(nif, emailAddress, name, addressId);
        return clientDB.addClient(client);
    }

    public boolean removeClient(String nif){
        return clientDB.removeClient(nif);
    }

    public Client getClientByEmail(String email){
        return clientDB.getClientByEmail(email);
    }

}