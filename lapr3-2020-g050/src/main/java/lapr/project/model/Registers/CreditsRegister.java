package lapr.project.model.Registers;

import lapr.project.data.CreditsDB;
import lapr.project.model.DomainClasses.Credits;

public class CreditsRegister {

    private final CreditsDB creditsDB;

    public CreditsRegister(){
        this.creditsDB = new CreditsDB();
    }

    public boolean addCredits(int id, int numberOfCredits, String clientNif) {
        Credits credits = new Credits(id, numberOfCredits, clientNif);
        return creditsDB.addCredits(credits);
    }

    public boolean removeCredits(int id){
        return creditsDB.removeCredits(id);
    }

    public Credits getCreditsByClientNif(String clientNif) { return creditsDB.getCreditsByClientNif(clientNif);}

    public boolean updateCredits(int id, int numberOfCredits, String clientNif){
        return creditsDB.updateCredits(id, numberOfCredits, clientNif);
    }


}
