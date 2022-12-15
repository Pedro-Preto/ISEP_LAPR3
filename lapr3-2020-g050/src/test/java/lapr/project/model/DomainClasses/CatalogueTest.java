package lapr.project.model.DomainClasses;

import lapr.project.controller.AddItemsToPharmacyStockController;
import lapr.project.controller.AddPharmacyController;
import lapr.project.controller.ApplicationEM;
import lapr.project.controller.MakeOrderController;
import lapr.project.model.BinaryTrees.AVL;
import lapr.project.model.Registers.ItemRegister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {
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

        platform.getStockItemsRegister().removeStockItems(5, 4);
        platform.getStockItemsRegister().removeStockItems(4, 4);
        platform.getItemRegister().removeItem(4);
        platform.getItemRegister().removeItem(5);
        platform.getStockRegister().removeStock(4);
        platform.getPharmacyRegister().removePharmacy("Farmacia do Catalogo");
        platform.getAddressRegister().removeAddress(15);

        addPharmacyController.addAddress(15, "Rua das Olivas", "012", "Lisboa", "Portugal");
        addPharmacyController.addPharmacy("Farmacia do Catalogo", "Faramacia", 15);
        addPharmacyController.addStock(4, "Farmacia do Catalogo");
        platform.getItemRegister().addItem(4, "Benuron", 5.0, 0.5);
        platform.getItemRegister().addItem(5, "Cegripe", 5.0, 0.5);
        platform.getItemRegister().addItem(6, "Vacina", 5.0, 0.5);
        addItemsToPharmacyStockController.addItemsToStock(4, 4, 5);
        addItemsToPharmacyStockController.addItemsToStock(5, 4, 5);
        addItemsToPharmacyStockController.addItemsToStock(6, 4, 5);


    }

    @AfterEach
    void tearDown() {
        platform.getStockItemsRegister().removeStockItems(6, 4);
        platform.getStockItemsRegister().removeStockItems(5, 4);
        platform.getStockItemsRegister().removeStockItems(4, 4);
        platform.getItemRegister().removeItem(4);
        platform.getItemRegister().removeItem(5);
        platform.getItemRegister().removeItem(6);
        platform.getStockRegister().removeStock(4);
        platform.getPharmacyRegister().removePharmacy("Farmacia do Catalogo");
        platform.getAddressRegister().removeAddress(15);

    }


    /**
     * Test of searchItemByName method, of class Catalogue.
     */
    @Test
    void testSearchItemByName() {
        System.out.println("searchItemByName\n");
        AVL<Item> avl = new Catalogue();
        Item i1 = platform.getItemRegister().getItemByName("Benuron");
        avl.insert(i1);
        Item i2 = platform.getItemRegister().getItemByName("Cegripe");
        avl.insert(i2);
        Item i3=platform.getItemRegister().getItemByName("Vacina");
        avl.insert(i3);

        Item e1 = ((Catalogue) avl).searchItemByName("Benuron");
        System.out.println(i1.toString() + "-Equal to-\n" + e1.toString());
        assertEquals(i1.toString(), e1.toString());

        Item e2 = ((Catalogue) avl).searchItemByName("Cegripe");
        System.out.println(i2.toString() + "-Equal to-\n" + e2.toString());
        assertEquals(i2.toString(), e2.toString());

        Item e3 = ((Catalogue) avl).searchItemByName("Vacina");
        System.out.println(i3.toString() + "-Equal to-\n" + e3.toString());
        assertEquals(i3.toString(), e3.toString());

        Item e4 = ((Catalogue) avl).searchItemByName("Vacina");
        System.out.println(i1.toString() + "-Not Equal to-\n" + e4.toString());
        assertNotEquals(i1.toString(), e4.toString());

        Item e5 = ((Catalogue) avl).searchItemByName("Benuron");
        System.out.println(i2.toString() + "-Not Equal to-\n" + e5.toString());
        assertNotEquals(i2.toString(), e5.toString());


    }


}