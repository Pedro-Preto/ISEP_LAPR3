package lapr.project.model.Registers;

import lapr.project.data.PharmacyDB;
import lapr.project.model.DomainClasses.Pharmacy;

import java.util.List;

public class PharmacyRegister {

    private final PharmacyDB pharmacyDB;

    public PharmacyRegister() {
        this.pharmacyDB = new PharmacyDB();
    }

    public boolean addPharmacy(String nif, String designation, int addressId) {
        Pharmacy pharmacy = new Pharmacy(nif, designation, addressId);
        return pharmacyDB.addPharmacy(pharmacy);
    }

    public boolean removePharmacy(String pharmacyNif) {
        return pharmacyDB.removePharmacy(pharmacyNif);
    }

    public Pharmacy getPharmacyByNif(String pharmacyNif){
        return pharmacyDB.getPharmacy(pharmacyNif);
    }

    public List<Pharmacy> getAllPharmacies(){
        return pharmacyDB.getAllPharmacies();
    }
}
