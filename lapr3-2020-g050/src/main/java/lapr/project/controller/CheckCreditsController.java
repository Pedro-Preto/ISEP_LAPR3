package lapr.project.controller;

import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Credits;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.ClientRegister;
import lapr.project.model.Registers.CreditsRegister;

public class CheckCreditsController {

    private final CreditsRegister creditsRegister;
    private final ClientRegister clientRegister;
    private final Platform platform;

    public CheckCreditsController() {
        ApplicationEM app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.creditsRegister = platform.getCreditsRegister();
        this.clientRegister = platform.getClientRegister();
    }

    public Integer checkCredits(){
        String clientEmail = platform.getAutorization().getUserSession().getUserEmail();
        Client client = clientRegister.getClientByEmail(clientEmail);
        Credits credits = creditsRegister.getCreditsByClientNif(client.getNif());
        return credits.getNumberOfCredits();
    }
}
