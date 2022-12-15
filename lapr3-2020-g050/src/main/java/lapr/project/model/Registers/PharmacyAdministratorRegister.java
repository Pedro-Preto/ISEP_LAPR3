package lapr.project.model.Registers;

import lapr.project.data.PharmacyAdministratorDB;
import lapr.project.model.DomainClasses.PharmacyAdministrator;

import java.util.List;

public class PharmacyAdministratorRegister {

    private final PharmacyAdministratorDB pharmacyAdministratorDB;

    public PharmacyAdministratorRegister() {
        this.pharmacyAdministratorDB=new PharmacyAdministratorDB();
    }

    public boolean addPharmacyAdministrator(String emailAddress, String name, String pharmacyNif) {
        PharmacyAdministrator pharmacyAdministrator = new PharmacyAdministrator(emailAddress, name, pharmacyNif);
        return pharmacyAdministratorDB.addPharmacyAdministrator(pharmacyAdministrator);
    }

    public boolean removePharmacyAdministrator(String emailAddress) {
        return pharmacyAdministratorDB.removePharmacyAdministrator(emailAddress);
    }

    public PharmacyAdministrator getPharmacyAdministrator(String email){
        return pharmacyAdministratorDB.getPharmacyAdministrator(email);
    }

    public List<PharmacyAdministrator> getPharmacyAdministratorByPharmacyNif(String pharmacyNif){
        return pharmacyAdministratorDB.getPharmacyAdministratorByPharmacyNif(pharmacyNif);
    }
}
