package lapr.project.controller;

import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.DomainClasses.Receipt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagePharmaciesControllerTest {

    private CheckOrderStatusController checkOrderStatusController;
    private AddPharmacyController addPharmacyController;
    private AddItemsToPharmacyStockController addItemsToPharmacyStockController;
    private LoginController loginController;
    private MakeOrderController makeOrderController;
    private RegistrationController registrationController;
    private RegisterClientController registerClientController;
    private ManagePharmaciesController managePharmaciesController;
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
        managePharmaciesController = new ManagePharmaciesController();
        platform.resetData();

        addPharmacyController.addAddress(3500, "Rua das Marias", "135", "Porto", "Portugal");
        addPharmacyController.addAddress(3501, "Rua das Madalenas", "1351", "Porto", "Portugal");


        addPharmacyController.addPharmacy("321321321", "Farmacia Torres", 3500);

        registerClientController.addClient("123123123", "joao1@isep.pt", "joao", 3501);

        registrationController.registerClient(new Client("123123123", "joao1@isep.pt", "joao", 3500), "ABC");
        platform.getCreditsRegister().addCredits(1, 5, "123123123");
        loginController.doLogin("joao1@isep.pt", "ABC");
        makeOrderController = new MakeOrderController();


    }


    @Test
    void getPharmacyReceipsById() {

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
        makeOrderController.makeReceipt(1);

        TreeMap<Item, Integer> otherMap = new TreeMap<>();
        otherMap.put(it11, 3);
        otherMap.put(it12, 5);

        makeOrderController.makeOrder(3521, otherMap, "321321321");
        makeOrderController.makeReceipt(2);

        Boolean r1 = managePharmaciesController.getPharmacyReceipsById("321321321");
/*
        List<Receipt> receiptsInDB = platform.getReceiptRegister().getReceiptByPharmacyNif("321321321");
        List<Receipt> receiptList = new ArrayList<>();

        for (Receipt r : receiptsInDB) {
            r = platform.getReceiptRegister().getReceipt(r.getId());
            receiptList.add(r);
        }*/
        assertTrue(r1, "Should be the same");

    }


    @AfterEach
    void tearDown() {
        platform.getAutorization().doLogout();

        platform.getReceiptRegister().removeReceipt(1);
        platform.getReceiptRegister().removeReceipt(2);

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

        platform.getAutorization().deleteUser("joao1@isep.pt");
        platform.getAutorization().deleteUser("admin");

        platform.getAddressRegister().removeAddress(3500);
        platform.getAddressRegister().removeAddress(3501);
    }

}