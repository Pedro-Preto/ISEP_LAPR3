package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Savepoint;

import static org.junit.jupiter.api.Assertions.*;

class AddPharmacyControllerTest {

    private AddPharmacyController addPharmacyController;
    private Platform platform;

    /*
    @BeforeAll
    static void setUp() {
        Platform platform = ApplicationEM.getInstance().getPlatform();
        AddPharmacyController apc =new AddPharmacyController();
        apc.addAddress(1,"Rua das Olivas", "012", "Lisboa", "Portugal");
        platform.getPharmacyRegister().addPharmacy("111222333", "Farmácia", 1);
    }
    */

    @BeforeEach
    void setUpControllers(){
        addPharmacyController = new AddPharmacyController();
        platform = ApplicationEM.getInstance().getPlatform();
    }


    @AfterEach
    void tearDown() {
        platform.getStockRegister().removeStock(22222);

        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyAdministrator@gmail.com");

        platform.getPharmacyRegister().removePharmacy("123456789");
        platform.getPharmacyRegister().removePharmacy("111222333");

        platform.getAddressRegister().removeAddress(22222);
    }


    @Test
    void addAddress() {
        boolean actual;
        platform.getAddressRegister().removeAddress(22222);
        actual = addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        assertTrue(actual);
        actual = addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        assertFalse(actual);
        platform.getAddressRegister().removeAddress(22222);
        actual = addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        assertTrue(actual);

    }

    @Test
    void addPharmacy() {
        boolean actual;
        platform.getAddressRegister().removeAddress(22222);
        actual = addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        assertFalse(actual);
        addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        platform.getPharmacyRegister().removePharmacy("123456789");
        actual = addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        assertTrue(actual);
        actual = addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        assertFalse(actual);
        platform.getPharmacyRegister().removePharmacy("123456789");
        actual = addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        assertTrue(actual);
    }

    @Test
    void addPharmacyAdministrator (){
        boolean actual;
        actual = addPharmacyController.addPharmacyAdministrator("pharmacyAdministrator@gmail.com", "pharmacyAdmin", "123456789");
        assertFalse(actual);
        addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        platform.getPharmacyAdministratorRegister().removePharmacyAdministrator("pharmacyAdministrator@gmail.com");
        actual = addPharmacyController.addPharmacyAdministrator("pharmacyAdministrator@gmail.com", "pharmacyAdmin", "123456789");
        assertTrue(actual);
        actual = addPharmacyController.addPharmacyAdministrator("pharmacyAdministrator@gmail.com", "pharmacyAdmin", "123456789");
        assertFalse(actual);

    }

    @Test
    void addStock (){
        boolean actual;
        actual = addPharmacyController.addStock(22222, "123456789");
        assertFalse(actual);
        addPharmacyController.addAddress(22222,"Rua das Olivas", "012", "Lisboa", "Portugal");
        addPharmacyController.addPharmacy("123456789", "Farmácia", 22222);
        platform.getStockRegister().removeStock(22222);
        actual = addPharmacyController.addStock(22222, "123456789");
        assertTrue(actual);
        actual = addPharmacyController.addStock(22222, "123456789");
        assertFalse(actual);

    }


}