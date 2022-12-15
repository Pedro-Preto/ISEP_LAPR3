package lapr.project.controller;

import lapr.project.model.DomainClasses.Catalogue;
import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CheckCatalogueControllerTest {

    private ApplicationEM app;
    private Platform platform;
    private Catalogue catalogue;
    private CheckCatalogueController checkCatalogueController;

    @BeforeEach
    void setUp() {
        this.app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.platform.resetData();
        checkCatalogueController = new CheckCatalogueController();

    }

    @Test
    void getCatalogue() {
        Catalogue catalogue = new Catalogue();
        assertEquals(catalogue, checkCatalogueController.getCatalogue(), "Both Catalogues should not be equal!");
        catalogue = platform.getCatalogue();
        assertEquals(catalogue, checkCatalogueController.getCatalogue(), "Both Catalogues should be equal!");
    }
}
