package lapr.project.controller;

import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class CheckOrderStatusControllerTest {

    private CheckOrderStatusController checkOrderStatusController;
    private AddPharmacyController addPharmacyController;
    private AddItemsToPharmacyStockController addItemsToPharmacyStockController;
    private LoginController loginController;
    private MakeOrderController makeOrderController;
    private RegistrationController registrationController;
    private RegisterClientController registerClientController;
    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();

        tearDown();

        checkOrderStatusController = new CheckOrderStatusController();
        addPharmacyController = new AddPharmacyController();
        addItemsToPharmacyStockController = new AddItemsToPharmacyStockController();
        registrationController = new RegistrationController();
        registerClientController = new RegisterClientController();
        loginController = new LoginController();

        platform.resetData();

        addPharmacyController.addAddress(3500, "Rua 6", "135", "Porto", "Portugal");
        addPharmacyController.addAddress(3501, "Rua 61", "1351", "Porto", "Portugal");


        addPharmacyController.addPharmacy("321321321", "Farmacia Torres", 3500);

        registerClientController.addClient("123123123", "joao1@isep.pt", "joao", 3501);

        registrationController.registerClient(new Client("123123123", "joao1@isep.pt", "joao", 3500), "ABC");
        platform.getCreditsRegister().addCredits(1, 5, "123123123");

        loginController.doLogin("joao1@isep.pt", "ABC");

        makeOrderController = new MakeOrderController();
    }


    @Test
    void checkClientOrderStatus() {

        Item it11 = new Item(3511, "ibuprofeno", 2.75, 0.25);
        Item it12 = new Item(3512, "paracetamol", 1.90, 0.40);

        platform.getItemRegister().addItem(3511, "ibuprofeno", 2.75, 0.25);
        platform.getItemRegister().addItem(3512, "paracetamol", 1.90, 0.40);

        addPharmacyController.addStock(3531, "321321321");

        addItemsToPharmacyStockController.addItemsToStock(3511, 3531, 24);
        addItemsToPharmacyStockController.addItemsToStock(3512, 3531, 90);

        TreeMap<Item, Integer> treeMap = new TreeMap<>();
        treeMap.put(it11, 3);

        makeOrderController.makeOrder(3522, treeMap, "321321321");
        makeOrderController.makeReceipt(3522);

        TreeMap<Item, Integer> otherMap = new TreeMap<>();
        otherMap.put(it11, 3);
        otherMap.put(it12, 5);

        makeOrderController.makeOrder(3521, otherMap, "321321321");
        makeOrderController.makeReceipt(3521);

        List<String> result = new ArrayList<>();
        String sta1 = platform.getOrderRegister().getOrder(3521).getStatus();
        String sta2 = platform.getOrderRegister().getOrder(3522).getStatus();

        result.add("["+sta1+"]");
        result.add("["+sta2+"]");

        platform.getOrderRegister().removeOrder(3521);
        platform.getOrderRegister().removeOrder(3522);
        platform.getOrderRegister().addOrder(3521, "waiting payment", 17.75, "123123123");
        platform.getOrderRegister().addOrder(3522, "waiting payment", 8.25, "123123123");

        List<String> expeted = new ArrayList<>();
        String s1 = checkOrderStatusController.checkClientOrderStatus(3521, "123123123").toString();
        String s2 = checkOrderStatusController.checkClientOrderStatus(3522, "123123123").toString();
        expeted.add(s1);
        expeted.add(s2);

        assertEquals(expeted, result, "The lists should have the same status");


    }


    @AfterEach
    void tearDown() {

        platform.getReceiptRegister().removeReceipt(3522);
        platform.getReceiptRegister().removeReceipt(3521);

        platform.getItemsOfOrderRegister().removeItemsOfOrder(3511, 3522);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(3512, 3522);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(3512, 3521);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(3511, 3521);

        platform.getOrderRegister().removeOrder(3522);
        platform.getOrderRegister().removeOrder(3521);

        platform.getStockItemsRegister().removeStockItems(3511, 3531);
        platform.getStockItemsRegister().removeStockItems(3512, 3531);

        platform.getStockRegister().removeStock(3531);

        platform.getItemRegister().removeItem(3511);
        platform.getItemRegister().removeItem(3512);

        platform.getCreditsRegister().removeCredits(1);
        platform.getPharmacyRegister().removePharmacy("321321321");

        platform.getClientRegister().removeClient("123123123");

        platform.getAddressRegister().removeAddress(3500);
        platform.getAddressRegister().removeAddress(3501);
    }

}