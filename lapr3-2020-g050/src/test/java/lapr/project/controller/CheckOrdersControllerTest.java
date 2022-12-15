package lapr.project.controller;

import lapr.project.data.OrderDB;
import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.autorization.model.UserSession;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class CheckOrdersControllerTest {

    private CheckOrdersController checkOrdersController;
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

        checkOrdersController = new CheckOrdersController();
        addPharmacyController = new AddPharmacyController();
        addItemsToPharmacyStockController = new AddItemsToPharmacyStockController();
        registrationController = new RegistrationController();
        registerClientController = new RegisterClientController();
        loginController = new LoginController();

        platform.resetData();

        addPharmacyController.addAddress(3212, "Rua 6", "135", "Porto", "Portugal");
        addPharmacyController.addAddress(3211, "Rua 61", "1351", "Porto", "Portugal");


        addPharmacyController.addPharmacy("321321321", "Farmacia Torres", 3212);

        registerClientController.addClient("123123123", "joao1@isep.pt","joao",3211);

        registrationController.registerClient(new Client("123123123", "joao1@isep.pt","joao",3212), "ABC");

        platform.getCreditsRegister().addCredits(1, 5, "123123123");

        loginController.doLogin("joao1@isep.pt","ABC");

        makeOrderController = new MakeOrderController();
    }

    @AfterEach
    void tearDown() {

        platform.getAutorization().doLogout();

        platform.getReceiptRegister().removeReceipt(6514);
        platform.getReceiptRegister().removeReceipt(6516);

        platform.getItemsOfOrderRegister().removeItemsOfOrder(3221,6514);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(3221,6516);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(3222,6516);

        platform.getOrderRegister().removeOrder(6514);
        platform.getOrderRegister().removeOrder(6516);

        platform.getStockItemsRegister().removeStockItems(3221,3221);
        platform.getStockItemsRegister().removeStockItems(3222,3221);

        platform.getStockRegister().removeStock(3221);

        platform.getItemRegister().removeItem(3221);
        platform.getItemRegister().removeItem(3222);

        platform.getCreditsRegister().removeCredits(1);
        platform.getPharmacyRegister().removePharmacy("321321321");

        platform.getClientRegister().removeClient("123123123");

        platform.getAutorization().deleteUser("joao1@isep.pt");

        platform.getAddressRegister().removeAddress(3212);
        platform.getAddressRegister().removeAddress(3211);

    }

    @Test
    void checkOrders() {

//        Item it11 = new Item(3221,"ibuprofeno",2.75,0.25);
//        Item it12 = new Item(3222,"paracetamol", 1.90, 0.40);
//
//        platform.getItemRegister().addItem(3221,"ibuprofeno",2.75,0.25);
//        platform.getItemRegister().addItem(3222,"ibuprofeno", 1.90, 0.40);
//
//        addPharmacyController.addStock(3331,"321321321");
//
//        addItemsToPharmacyStockController.addItemsToStock(3221,3331,24);
//        addItemsToPharmacyStockController.addItemsToStock(3222,3331,90);
        Item it1111 = new Item(3221, "ibuprofeno", 2.75, 0.25);
        Item it1112 = new Item(3222, "paracetamol", 1.90, 0.40);

        addPharmacyController.addStock(3221, "321321321");

        platform.getItemRegister().addItem(3221, "ibuprofeno", 2.75, 0.25);
        platform.getItemRegister().addItem(3222, "paracetamol", 1.90, 0.40);

        addItemsToPharmacyStockController.addItemsToStock(3221,3221,24);
        addItemsToPharmacyStockController.addItemsToStock(3222,3221,90);

        TreeMap<Item, Integer> treeMap = new TreeMap<>();
        treeMap.put(it1111,3);

        makeOrderController.makeOrder(6514,treeMap,"321321321");
        makeOrderController.makeReceipt(6514);

        TreeMap<Item, Integer> otherMap = new TreeMap<>();
        otherMap.put(it1111,3);
        otherMap.put(it1112,5);

        makeOrderController.makeOrder(6516,otherMap,"321321321");
        makeOrderController.makeReceipt(6516);

        List<Order> result = checkOrdersController.checkOrders("321321321");

        List<Order> expected = new ArrayList<>();
        expected.add(new Order(6516,"Waiting Delivery",17.75,"123123123"));
        expected.add(new Order(6514,"Waiting Delivery",8.25,"123123123"));

        for(Order order : expected){
            boolean contains = result.contains(order);
            assertTrue(contains, "The list should have all of the items on this list");
        }

    }


}