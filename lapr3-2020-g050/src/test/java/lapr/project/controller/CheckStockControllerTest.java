package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckStockControllerTest {

    private ApplicationEM app;
    private Platform platform;
    private CheckStockController checkStockController;
    private AddItemsToPharmacyStockController addItemsToPharmacyStockController;
    private AddPharmacyController addPharmacyController;
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        this.app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.addItemsToPharmacyStockController = new AddItemsToPharmacyStockController();
        this.addPharmacyController = new AddPharmacyController();
        this.checkStockController = new CheckStockController();
        this.registrationController = new RegistrationController();

        platform.getStockItemsRegister().removeStockItems(3803,3850);
        platform.getStockItemsRegister().removeStockItems(3802,3850);
        platform.getStockItemsRegister().removeStockItems(3801,3850);
        platform.getItemRegister().removeItem(3803);
        platform.getItemRegister().removeItem(3802);
        platform.getItemRegister().removeItem(3801);
        platform.getStockRegister().removeStock(3850);
        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyadmin");
        platform.getPharmacyRegister().removePharmacy("984313411");
        platform.getAddressRegister().removeAddress(3850);


        addPharmacyController.addAddress(3850,"Rua das Olivas", "012", "Lisboa", "Portugal");
        addPharmacyController.addPharmacy("984313411", "Faramacia",3850);
        addPharmacyController.addPharmacyAdministrator("pharmacyadmin", "admName", "984313411");
        registrationController.registerPharmacyAdmin(platform.getPharmacyAdministratorRegister().getPharmacyAdministrator("pharmacyadmin"),"pharmacyadmin");
        addPharmacyController.addStock(3850,"984313411");
        platform.getItemRegister().addItem(3803,"Brufen", 5.0, 0.5);
        platform.getItemRegister().addItem(3802,"Benuron", 5.0, 0.5);
        platform.getItemRegister().addItem(3801,"Cegripe", 5.0, 0.5);
        addItemsToPharmacyStockController.addItemsToStock(3803,3850,5);
        addItemsToPharmacyStockController.addItemsToStock(3802,3850,25);

        platform.getAutorization().doLogin("pharmacyadmin" , "pharmacyadmin");
    }

    @Test
    void getStock() {
        List<StockItems> expected = new ArrayList<>();
        StockItems s1 = new StockItems(3803,3850,5);
        StockItems s2 = new StockItems(3802,3850,25);
        expected.add(s2);
        expected.add(s1);


        List<StockItems> actual = checkStockController.getStock();

        assertEquals(expected.toString(), actual.toString(), "It should be equal!");

        addItemsToPharmacyStockController.addItemsToStock(3801,3850,2);
        actual = checkStockController.getStock();

        assertNotEquals(expected.toString(), actual.toString(), "It should not be equal!");
    }

    @AfterEach
    void tearDown(){
        platform.getStockItemsRegister().removeStockItems(3803,3850);
        platform.getStockItemsRegister().removeStockItems(3802,3850);
        platform.getStockItemsRegister().removeStockItems(3801,3850);
        platform.getItemRegister().removeItem(3803);
        platform.getItemRegister().removeItem(3802);
        platform.getItemRegister().removeItem(3801);
        platform.getStockRegister().removeStock(3850);
        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyadmin");
        platform.getPharmacyRegister().removePharmacy("984313411");
        platform.getAddressRegister().removeAddress(3850);

        platform.getAutorization().doLogout();
    }
}