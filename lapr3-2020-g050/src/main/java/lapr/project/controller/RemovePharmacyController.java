package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.PharmacyAdministratorRegister;
import lapr.project.model.Registers.PharmacyRegister;

public class RemovePharmacyController {

    private final PharmacyRegister pharmacyRegister;
    private final PharmacyAdministratorRegister pharmacyAdministratorRegister;

    public RemovePharmacyController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.pharmacyRegister = platform.getPharmacyRegister();
        this.pharmacyAdministratorRegister = platform.getPharmacyAdministratorRegister();
    }

    public Boolean removePharmacy(String pharmacyNif) {
        return this.pharmacyRegister.removePharmacy(pharmacyNif);
    }

    public Boolean removePharmacyAdministrator(String pharmacyAdminEmail) {
        return this.pharmacyAdministratorRegister.removePharmacyAdministrator(pharmacyAdminEmail);
    }

}




