package lapr.project.controller;

import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.PharmacyRegister;
import lapr.project.model.Registers.ReceiptRegister;
import lapr.project.utils.EmailSystem;

public class ManagePharmaciesController {

    private final ReceiptRegister receiptRegister;

    private final PharmacyRegister pharmacyRegister;

    public ManagePharmaciesController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.receiptRegister = platform.getReceiptRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();
    }

    public Boolean getPharmacyReceipsById(String pharNIF) {
        String pharmacyName = pharmacyRegister.getPharmacyByNif(pharNIF).getDesignation();
        String subject = "Pharmacy List of Receips";
        String message = receiptRegister.getReceiptByPharmacyNif(pharNIF).toString();
        return EmailSystem.sendEmail(pharmacyName, subject, message);
    }
}
