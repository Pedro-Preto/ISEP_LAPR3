package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.DomainClasses.Order;
import lapr.project.model.Registers.ItemRegister;
import lapr.project.model.Registers.ItemsOfOrderRegister;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MakeOrderControllerTest {

    private static CheckOrdersController checkOrdersController;
    private static AddPharmacyController addPharmacyController;
    private static AddItemsToPharmacyStockController addItemsToPharmacyStockController;
    private static LoginController loginController;
    private static MakeOrderController makeOrderController;
    private static RegistrationController registrationController;
    private static Platform platform;
    private static RegisterClientController registerClientController;
    private static ItemsOfOrderRegister itemsOfOrderRegister;
    private static ItemRegister itemRegister;

    @BeforeEach
    void setUp() {
        platform = ApplicationEM.getInstance().getPlatform();
        itemsOfOrderRegister = platform.getItemsOfOrderRegister();
        itemRegister = platform.getItemRegister();

        checkOrdersController = new CheckOrdersController();
        addPharmacyController = new AddPharmacyController();
        addItemsToPharmacyStockController = new AddItemsToPharmacyStockController();

        loginController = new LoginController();

        addPharmacyController.addAddress(215, "Rua 6", "135", "Porto", "Portugal");
        addPharmacyController.addAddress(216, "Rua 8", "023", "Lisboa", "Portugal");
        addPharmacyController.addAddress(217, "Rua 9", "0223", "Lisboa", "Portugal");
        addPharmacyController.addAddress(218, "Rua 123", "0213", "Lisboa", "Portugal");
        addPharmacyController.addAddress(230, "Rua Make", "427", "Lisboa", "Portugal");


        addPharmacyController.addPharmacy("294735970", "Farmacia Torres", 215);
        addPharmacyController.addPharmacy("389646853", "Farmacia Sousa", 216);

        registrationController = new RegistrationController();
        registerClientController = new RegisterClientController();

        registerClientController.addClient("839045739", "joao423@isep.pt", "joao", 215);
        registrationController.registerClient(new Client("839045739", "joao423@isep.pt", "joao", 230), "ABC");


        platform.getScooterParkRegister().addScooterPark(111222, "389646853", 1500);
        platform.getScooterParkRegister().addScooterPark(222333, "294735970", 2400);

        platform.getScooterRegister().addScooter(12131, 5000, 5, 600, 5000, 5, 4, 150, "389646853", "434");
        platform.getScooterRegister().addScooter(65767, 6, 3, 2, 2, 5, 4, 15, "294735970", "43434");


        platform.getParkSlotRegister().addParkSlot(656234, 111222, 12131);
        platform.getParkSlotRegister().addParkSlot(345454, 222333, 65767);

        loginController.doLogin("joao423@isep.pt", "ABC");

        makeOrderController = new MakeOrderController();

    }

    @AfterEach
    void tearDown() {
        platform.resetData();
        platform.getStockItemsRegister().removeStockItems(111, 24);
        platform.getStockItemsRegister().removeStockItems(112, 24);
        platform.getStockItemsRegister().removeStockItems(111, 25);
        platform.getStockItemsRegister().removeStockItems(112, 25);

        platform.getItemsOfOrderRegister().removeItemsOfOrder(111, 12);
        platform.getItemsOfOrderRegister().removeItemsOfOrder(112, 12);

        platform.getDeliveryRegister().removeDelivery(12);

        platform.getReceiptRegister().removeReceipt(23);

        platform.getOrderRegister().removeOrder(12);
        platform.getOrderRegister().removeOrder(22);

        platform.getStockRegister().removeStock(24);
        platform.getStockRegister().removeStock(25);

        platform.getItemRegister().removeItem(111);
        platform.getItemRegister().removeItem(112);



        platform.getCreditsRegister().removeCredits(77);


        platform.getParkSlotRegister().removeParkSlot(656234);
        platform.getParkSlotRegister().removeParkSlot(345454);


        platform.getScooterRegister().removeScooter(65767);
        platform.getScooterRegister().removeScooter(12131);

        platform.getScooterParkRegister().removeScooterPark(222333);
        platform.getScooterParkRegister().removeScooterPark(111222);



        platform.getClientRegister().removeClient("839045739");
        platform.getClientRegister().removeClient("NEW");

        platform.getCourierRegister().removeCourier("1#");
        platform.getCourierRegister().removeCourier("2#");
        platform.getCourierRegister().removeCourier("3#");

        platform.getLandPathRegister().removeLandPath(123450, 12433143);
        platform.getLandPathRegister().removeLandPath(123450, 43532113);


        platform.getGeoLocationRegister().removeGeoLocation(12433143);
        platform.getGeoLocationRegister().removeGeoLocation(43532113);
        platform.getGeoLocationRegister().removeGeoLocation(123450);



        platform.getPharmacyRegister().removePharmacy("294735970");
        platform.getPharmacyRegister().removePharmacy("389646853");

        platform.getAddressRegister().removeAddress(215);
        platform.getAddressRegister().removeAddress(216);
        platform.getAddressRegister().removeAddress(217);
        platform.getAddressRegister().removeAddress(218);
        platform.getAddressRegister().removeAddress(230);

        platform.getAutorization().deleteUser("joao423@isep.pt");
    }

    @Test
    void pickItemsOfOrder() {
        TreeMap<String, Integer> itemsAndQuantity = new TreeMap<>();
        itemsAndQuantity.put("remedio1", 2);
        itemsAndQuantity.put("remedio2", 1);
        platform.getItemRegister().addItem(111, "remedio1", 2.75, 0.25);
        platform.getItemRegister().addItem(112, "remedio2", 1.90, 0.40);

        TreeMap<Item, Integer> expected = new TreeMap<>();
        expected.put(new Item(111, "remedio1", 2.75, 0.25), 2);
        expected.put(new Item(112, "remedio2", 1.90, 0.40), 1);

        Map<Item, Integer> actual = makeOrderController.pickItemsOfOrder(itemsAndQuantity);

        assertEquals(expected, actual);
    }

    @Test
    void checkAvailability() {
        Item it111 = new Item(111, "remedio1", 2.75, 0.25);
        Item it112 = new Item(112, "remedio2", 1.90, 0.40);

        addPharmacyController.addStock(24, "294735970");
        addPharmacyController.addStock(25, "389646853");

        platform.getItemRegister().addItem(111, "remedio1", 2.75, 0.25);
        platform.getItemRegister().addItem(112, "remedio2", 1.90, 0.40);


        addItemsToPharmacyStockController.addItemsToStock(111, 24, 24);
        addItemsToPharmacyStockController.addItemsToStock(112, 24, 90);
        addItemsToPharmacyStockController.addItemsToStock(111, 25, 2);
        addItemsToPharmacyStockController.addItemsToStock(112, 25, 1);

        TreeMap<Item, Integer> itemsOrdered = new TreeMap<>();
        itemsOrdered.put(it111, 3);
        itemsOrdered.put(it112, 1);

        boolean result = makeOrderController.checkAvailability(itemsOrdered, "294735970");
        assertTrue(result, "It should be true! There is one pharmacy with stock");
        result = makeOrderController.checkAvailability(itemsOrdered, "389646853");
        assertFalse(result);

        itemsOrdered.clear();
        itemsOrdered.put(it111, 25);
        itemsOrdered.put(it112, 1);

        result = makeOrderController.checkAvailability(itemsOrdered, "294735970");
        assertFalse(result, "It should be false! Does not exist in any pharmacy the items ordered");
        result = makeOrderController.checkAvailability(itemsOrdered, "389646853");
        assertFalse(result, "It should be false! Does not exist in any pharmacy the items ordered");

        itemsOrdered.clear();
        itemsOrdered.put(it111, 100);
        result = makeOrderController.checkAvailability(itemsOrdered, "389646853");
        assertFalse(result, "It should be false! Does not exist in any pharmacy the items ordered");

        //RESET DATABASE

    }

    @Test
    void makeOrder() {
        Item it111 = new Item(111, "remedio1", 2.75, 0.25);
        Item it112 = new Item(112, "remedio2", 1.90, 0.40);

        addPharmacyController.addStock(24, "294735970");
        addPharmacyController.addStock(25, "389646853");

        platform.getItemRegister().addItem(111, "remedio1", 2.75, 0.25);
        platform.getItemRegister().addItem(112, "remedio2", 1.90, 0.40);

        addItemsToPharmacyStockController.addItemsToStock(111, 24, 24);
        addItemsToPharmacyStockController.addItemsToStock(112, 24, 90);
        addItemsToPharmacyStockController.addItemsToStock(111, 25, 2);
        addItemsToPharmacyStockController.addItemsToStock(112, 25, 1);

        TreeMap<Item, Integer> itemsOrdered = new TreeMap<>();
        itemsOrdered.put(it111, 3);
        itemsOrdered.put(it112, 1);

        boolean result = makeOrderController.makeOrder(12, itemsOrdered, "294735970");
        assertTrue(result, "The result should be true, the order should be added");

        result = makeOrderController.makeOrder(12, itemsOrdered, "294735970");
        assertFalse(result, "The result should be false, already exist an order with id:1");

        itemsOrdered.clear();
        itemsOrdered.put(it111, 25);
        itemsOrdered.put(it112, 1);


        /*platform.getScooterRegister().addScooter(9379,24.0,3.3,4.7,24.0,134.5,4.3,20.0,"294735970","1111110010010100");

        result = makeOrderController.makeOrder(22,itemsOrdered,"389646853");
        assertFalse(result, "The result should be false, the order products are not available on stock.");

        */

    }

    @Test
    void removeItemsFromPharmacyStock() {
        Item it111 = new Item(111, "remedio1", 2.75, 0.25);
        Item it112 = new Item(112, "remedio2", 1.90, 0.40);

        addPharmacyController.addStock(24, "294735970");
        addPharmacyController.addStock(25, "389646853");

        platform.getItemRegister().addItem(111, "remedio1", 2.75, 0.25);
        platform.getItemRegister().addItem(112, "remedio2", 1.90, 0.40);

        addItemsToPharmacyStockController.addItemsToStock(111, 24, 24);
        addItemsToPharmacyStockController.addItemsToStock(112, 24, 90);
        addItemsToPharmacyStockController.addItemsToStock(111, 25, 2);
        addItemsToPharmacyStockController.addItemsToStock(112, 25, 1);

        TreeMap<Item, Integer> itemsOrdered = new TreeMap<>();
        itemsOrdered.put(it111, 3);
        itemsOrdered.put(it112, 1);
        makeOrderController.makeOrder(12, itemsOrdered, "294735970");

        Order order = platform.getOrderRegister().getOrder(12);
        Map<Item, Integer> itemAndQuantity = new HashMap<>();
        for (ItemsOfOrder itemsOfOrder : itemsOfOrderRegister.getAllItemsOfOrder(order.getId())) {
            itemAndQuantity.put(itemRegister.getItem(itemsOfOrder.getItemId()), itemsOfOrder.getItemsQuantity());
        }

        List<StockItems> actual = platform.getStockItemsRegister().getAllItemsOfStock(24);
        List<StockItems> expected = new ArrayList<>();
        expected.add(new StockItems(111, 24, 21));
        expected.add(new StockItems(112, 24, 89));

        assertEquals(expected, actual);
    }

    @Test
    void makeReceipt() {
        Item it111 = new Item(111, "remedio1", 2.75, 0.25);
        Item it112 = new Item(112, "remedio2", 1.90, 0.40);

        addPharmacyController.addStock(24, "294735970");
        addPharmacyController.addStock(25, "389646853");

        platform.getItemRegister().addItem(111, "remedio1", 2.75, 0.25);
        platform.getItemRegister().addItem(112, "remedio2", 1.90, 0.40);

        addItemsToPharmacyStockController.addItemsToStock(111, 24, 24);
        addItemsToPharmacyStockController.addItemsToStock(112, 24, 90);
        addItemsToPharmacyStockController.addItemsToStock(111, 25, 2);
        addItemsToPharmacyStockController.addItemsToStock(112, 25, 1);

        TreeMap<Item, Integer> itemsOrdered = new TreeMap<>();
        itemsOrdered.put(it111, 3);
        itemsOrdered.put(it112, 1);

        platform.getCreditsRegister().addCredits(77, 5, "839045739");

        makeOrderController.makeOrder(12, itemsOrdered, "294735970");

        Order order = platform.getOrderRegister().getOrder(12);

        Receipt actual = makeOrderController.makeReceipt(23);
        Receipt expected = new Receipt(23, order.getItemsPrice() + Delivery.getShippingCosts(), order.getId(), order.getClientNif(), "294735970");

        assertEquals(expected, actual);
    }

    /*@Test
    void makeDelivery(){

        Item it111 = new Item(111,"remedio1",2.75,0.25);
        Item it112 = new Item(112,"remedio2", 1.90, 0.40);

        addPharmacyController.addStock(24,"294735970");
        addPharmacyController.addStock(25,"389646853");

        platform.getItemRegister().addItem(111,"remedio1",2.75,0.25);
        platform.getItemRegister().addItem(112,"remedio2", 1.90, 0.40);

        addItemsToPharmacyStockController.addItemsToStock(111,24,24);
        addItemsToPharmacyStockController.addItemsToStock(112,24,90);
        addItemsToPharmacyStockController.addItemsToStock(111,25,2);
        addItemsToPharmacyStockController.addItemsToStock(112,25,1);

        TreeMap<Item,Integer> itemsOrdered = new TreeMap<>();
        itemsOrdered.put(it111,3);
        itemsOrdered.put(it112,1);

        platform.getCreditsRegister().addCredits(77,5,"839045739");

        makeOrderController.makeOrder(12,itemsOrdered,"294735970");

        Order order = platform.getOrderRegister().getOrder(12);

        boolean result = makeOrderController.makeDelivery(3.00);

        Delivery expected = new Delivery(12,"processed",3.00);
        Delivery actual = platform.getDeliveryRegister().getDelivery(12);

        assertEquals(expected,actual);
    }*/

    @Test
    void getNearestPharmacy() {

        platform.getCourierRegister().addCourier("2#", "2#", "2#@lapr.pt", "2#", "2#", 70.2, 100, "389646853");
        platform.getCourierRegister().addCourier("3#", "3#", "3#@lapr.pt", "3#", "3#", 68.2, 100, "294735970");

        //Phamacies Geo
        platform.getGeoLocationRegister().addGeoLocation(12433143, 41.13000, -8.60664, 104, 215);
        platform.getGeoLocationRegister().addGeoLocation(43532113, 41.3173, -8.66831, 81, 216);

        //Client Geo
        platform.getGeoLocationRegister().addGeoLocation(123450, 41.12930, -8.60510, 109, 230);

        platform.getLandPathRegister().addLandPath(123450, 12433143, 0.0, 0.0, 0.0, 0.5, 0.0);
        platform.getLandPathRegister().addLandPath(123450, 43532113, 0.0, 0.0, 0.0, 0.5, 0.0);


        Pharmacy p1 = makeOrderController.getNearestPharmacy();

        Pharmacy p2 = platform.getPharmacyRegister().getPharmacyByNif("294735970");

        assertEquals(p1.toString(), p2.toString(),"Should be equal");


        platform.getLandPathRegister().removeLandPath(123450, 12433143);
        platform.getLandPathRegister().removeLandPath(123450, 43532113);


        platform.getGeoLocationRegister().removeGeoLocation(12433143);
        platform.getGeoLocationRegister().removeGeoLocation(43532113);
        platform.getGeoLocationRegister().removeGeoLocation(123450);
    }
}