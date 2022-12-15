package lapr.project.controller;

import lapr.project.model.DomainClasses.Catalogue;
import lapr.project.model.DomainClasses.Platform;

public class CheckCatalogueController {

    private final Catalogue catalogue;

    public CheckCatalogueController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.catalogue = platform.getCatalogue();
    }

    public Catalogue getCatalogue(){
        return this.catalogue;
    }

}
