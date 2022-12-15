package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.autorization.AutorizationFacade;
import lapr.project.utils.EmailSystem;
import lapr.project.utils.PasswordGeneratorAlgorithm;

public class RegistrationController {

    private final AutorizationFacade at;

    public RegistrationController() {
        ApplicationEM app = ApplicationEM.getInstance();
        this.at = app.getPlatform().getAutorization();
    }

    public boolean registerCourier(Courier courier){
        if(!at.hasRegisteredUser(courier.getEmailAddress())){
            String pwd = PasswordGeneratorAlgorithm.generatesPassword();
            at.registerUserWithRole(courier.getName(), courier.getEmailAddress(),pwd, Constants.ROLE_COURIER);
            return EmailSystem.sendEmail(courier.getEmailAddress(),"PASSWORD",
                    "Your login is your email address and your password is " + pwd + ".\n");
        }
        return false;
    }

    public boolean registerCourier(Courier courier, String pwd){
        if(!at.hasRegisteredUser(courier.getEmailAddress())){
            return at.registerUserWithRole(courier.getName(), courier.getEmailAddress(),pwd, Constants.ROLE_COURIER);
        }
        return false;
    }


    public boolean registerClient(Client client, String pwd){
        if(!at.hasRegisteredUser(client.getEmailAddress()))
            return at.registerUserWithRole(client.getName(), client.getEmailAddress(), pwd, Constants.ROLE_CLIENT);
        return false;
    }

    public boolean registerPharmacyAdmin(PharmacyAdministrator pharmacyAdministrator){
        if(!at.hasRegisteredUser(pharmacyAdministrator.getEmailAddress())){
            String pwd = PasswordGeneratorAlgorithm.generatesPassword();
            if(at.registerUserWithRole(pharmacyAdministrator.getName(),
                    pharmacyAdministrator.getEmailAddress(),pwd, Constants.ROLE_PHARMACY_ADMINISTRATOR))
                return EmailSystem.sendEmail(pharmacyAdministrator.getEmailAddress(),"PASSWORD",
                        "Your login is your email address and your password is " + pwd + ".\n");
        }
        return false;
    }

    public boolean registerPharmacyAdmin(PharmacyAdministrator pharmacyAdministrator, String pwd){
        if(!at.hasRegisteredUser(pharmacyAdministrator.getEmailAddress())){
            return at.registerUserWithRole(pharmacyAdministrator.getName(),
                    pharmacyAdministrator.getEmailAddress(),pwd, Constants.ROLE_PHARMACY_ADMINISTRATOR);
        }
        return false;
    }

    public boolean deleteUser(String emailAddress){
        return at.deleteUser(emailAddress);
    }
}
