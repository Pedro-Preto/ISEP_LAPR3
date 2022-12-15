package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.AddressRegister;
import lapr.project.model.Registers.PharmacyAdministratorRegister;
import lapr.project.model.Registers.PharmacyRegister;
import lapr.project.model.Registers.StockRegister;

public class AddPharmacyController {

    private final PharmacyRegister pharmacyRegister;
    private final AddressRegister addressRegister;
    private final PharmacyAdministratorRegister pharmacyAdministratorRegister;
    private final StockRegister stockRegister;

    public AddPharmacyController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.addressRegister = platform.getAddressRegister();
        this.pharmacyAdministratorRegister = platform.getPharmacyAdministratorRegister();
        this.stockRegister = platform.getStockRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();
    }

    public Boolean addAddress (int id, String street, String doorNumber, String city, String country){
        return addressRegister.addAddress(id, street, doorNumber, city, country);
    }

    public Boolean addPharmacy (String nif, String designation, int addressId){
        return pharmacyRegister.addPharmacy(nif,designation,addressId);
    }

    public Boolean addPharmacyAdministrator (String email, String name, String pharmacyNif){
        return pharmacyAdministratorRegister.addPharmacyAdministrator(email, name, pharmacyNif);
    }

    public Boolean addStock (int id, String pharmacyNif){
        return stockRegister.addStock(id, pharmacyNif);
    }

}
