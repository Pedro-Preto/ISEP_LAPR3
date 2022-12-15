package lapr.project.controller;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.DomainClasses.StockItems;
import org.junit.jupiter.api.*;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class AddItemsToPharmacyStockControllerTest {

    private ApplicationEM app;
    private AddItemsToPharmacyStockController addItemsToPharmacyStockController;
    private Platform platform;
    private AddPharmacyController addPharmacyController;


    @BeforeEach
    void setUp() {
        this.app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.addItemsToPharmacyStockController = new AddItemsToPharmacyStockController();
        this.addPharmacyController = new AddPharmacyController();


        platform.getStockItemsRegister().removeStockItems(4, 4);
        platform.getItemRegister().removeItem(4);
        platform.getStockRegister().removeStock(4);
        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyadmin");
        platform.getPharmacyRegister().removePharmacy("p");
        platform.getAddressRegister().removeAddress(15);


        addPharmacyController.addAddress(15, "Rua das Olivas", "012", "Lisboa", "Portugal");
        addPharmacyController.addPharmacy("p", "Faramacia", 15);
        addPharmacyController.addPharmacyAdministrator("pharmacyadmin", "admName", "p");
        addPharmacyController.addStock(4, "p");
        platform.getItemRegister().addItem(4, "Benuron", 5.0, 0.5);
        platform.getItemRegister().addItem(5, "Vacina", 5.0, 0.5);

        //platform.getAutorization().doLogin("pharmacyadmin", "pharmacyadmin");
    }

    @Test
    void addItemsToStock() {
        boolean actual;
        actual = addItemsToPharmacyStockController.addItemsToStock(4, 4, 5);

        assertTrue(actual, "Must be added");
        actual = addItemsToPharmacyStockController.addItemsToStock(4, 4, 5);

        assertFalse(actual, "Must not be added");
        platform.getStockItemsRegister().removeStockItems(4, 4);
        actual = addItemsToPharmacyStockController.addItemsToStock(4, 4, 5);

        assertTrue(actual, "Must be added");
        platform.getStockItemsRegister().removeStockItems(4, 4);

    }

    @Test
    void updateStock() {
        addItemsToPharmacyStockController.addItemsToStock(4, 4, 5);
        addItemsToPharmacyStockController.addItemsToStock(5, 4, 5);
        TreeMap<Item, Integer> treeMap = new TreeMap<>();
        Item i1 = platform.getItemRegister().getItem(4);
        Item i2 = platform.getItemRegister().getItem(5);
        treeMap.put(i1, 1);
        treeMap.put(i2, 2);
        addItemsToPharmacyStockController.updateStock(treeMap, 4, 4, 3);
        int si1 = platform.getStockItemsRegister().getStockItem(4, 4).getItemsQuantity();
        addItemsToPharmacyStockController.updateStock(treeMap, 4, 5, 3);
        int si2 = platform.getStockItemsRegister().getStockItem(5, 4).getItemsQuantity();
        assertEquals(3, si1);
        assertEquals(3, si2);

    }

    @AfterEach
    void tearDown() {
        platform.getStockItemsRegister().removeStockItems(5, 4);
        platform.getStockItemsRegister().removeStockItems(4, 4);
        platform.getItemRegister().removeItem(4);
        platform.getItemRegister().removeItem(5);
        platform.getStockRegister().removeStock(4);
        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyadmin");
        platform.getPharmacyRegister().removePharmacy("p");
        platform.getAddressRegister().removeAddress(15);

        //platform.getAutorization().doLogout();
    }


}
