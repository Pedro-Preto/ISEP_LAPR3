
package lapr.project.controller;

import lapr.project.model.DomainClasses.*;
import lapr.project.model.Registers.*;
import lapr.project.utils.EmailSystem;

import java.util.*;


public class MakeOrderController {

    private final Platform platform;
    private final ItemRegister itemRegister;
    private CreditsRegister creditsRegister;
    private final PharmacyRegister pharmacyRegister;
    private final StockRegister stockRegister;
    private final StockItemsRegister stockItemsRegister;
    private final OrderRegister orderRegister;
    private final ItemsOfOrderRegister itemsOfOrderRegister;
    private final Client client;
    private Pharmacy pharmacy;
    private Receipt receipt;
    private Order order;
    private Delivery delivery;

    public MakeOrderController() {
        ApplicationEM app = ApplicationEM.getInstance();
        this.platform = app.getPlatform();
        this.itemRegister = platform.getItemRegister();
        this.creditsRegister = platform.getCreditsRegister();
        ClientRegister clientRegister = platform.getClientRegister();
        this.pharmacyRegister = platform.getPharmacyRegister();
        this.stockRegister = platform.getStockRegister();
        this.stockItemsRegister = platform.getStockItemsRegister();
        this.orderRegister = platform.getOrderRegister();
        this.itemsOfOrderRegister = platform.getItemsOfOrderRegister();
        ReceiptRegister receiptRegister = platform.getReceiptRegister();
        String clientEmail = platform.getAutorization().getUserSession().getUserEmail();
        client = clientRegister.getClientByEmail(clientEmail);
    }

    public Map<Item, Integer> pickItemsOfOrder(Map<String, Integer> itemNameItemQuantity) {
        Map<Item, Integer> itemAndQuantity = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : itemNameItemQuantity.entrySet())
            itemAndQuantity.put(itemRegister.getItemByName(entry.getKey()), entry.getValue());
        return itemAndQuantity;
    }

    public boolean makeOrder(int id, Map<Item, Integer> itemAndQuantity, String pharmacyNif) {
        Map<Item, Integer> aux;
        boolean orderAdded;
        boolean itemAdded;
        Pharmacy pharmacy = pharmacyRegister.getPharmacyByNif(pharmacyNif);
        this.order = null;

        double price = 0;
        for (Item item : itemAndQuantity.keySet()) {
            price += (item.getPrice() * itemAndQuantity.get(item));
        }
        if (checkAvailability(itemAndQuantity, pharmacyNif)) {
            orderAdded = orderRegister.addOrder(id, "waiting payment", price, this.client.getNif());
            if (orderAdded) {
                for (Item item : itemAndQuantity.keySet()) {
                    itemAdded = itemsOfOrderRegister.addItemsOfOrder(item.getId(), id, itemAndQuantity.get(item));
                    if (!itemAdded)
                        return false;
                }
                if (!removeItemsFromPharmacyStock(itemAndQuantity, pharmacyNif))
                    return false;
                this.pharmacy = pharmacy;
                this.order = orderRegister.getOrder(id);
                return true;
            }
        } else {
            Map<String, Map<Item, Integer>> pharmaciesWithItemsOnStock = getPharmaciesWithItemsOnStock(itemAndQuantity, pharmacyRegister.getPharmacyByNif(pharmacyNif));
            orderAdded = orderRegister.addOrder(id, "waiting payment", price, this.client.getNif());
            if (orderAdded) {
                for (String ph : pharmaciesWithItemsOnStock.keySet()) {
                    aux = new HashMap<>();
                    for (Item itm : pharmaciesWithItemsOnStock.get(ph).keySet()) {
                        aux.put(itm, pharmaciesWithItemsOnStock.get(ph).get(itm));
                    }
                    Pharmacy phar = platform.getPharmacyRegister().getPharmacyByNif(ph);
                    List<PharmacyAdministrator> administrators = platform.getPharmacyAdministratorRegister().getPharmacyAdministratorByPharmacyNif(phar.getNif());
                    for (PharmacyAdministrator pa : administrators){
                        EmailSystem.sendEmail(pa.getEmailAddress(),"ITEMS REQUEST", "Items needed:\n" + aux.toString() + "\n");
                    }
                    if (!removeItemsFromPharmacyStock(aux, ph))
                        return false;
                }
                for (Item item : itemAndQuantity.keySet()) {
                    itemAdded = itemsOfOrderRegister.addItemsOfOrder(item.getId(), id, itemAndQuantity.get(item));
                    if (!itemAdded)
                        return false;
                }
                this.pharmacy = pharmacy;
                this.order = orderRegister.getOrder(id);
                return true;
            }
        }
        return false;
    }

    public boolean checkAvailability(Map<Item, Integer> itemAndQuantity, String pharmacyNif) {

        Stock stock = stockRegister.getStockByPharmacyNif(pharmacyNif);
        List<StockItems> stockItemsList = stockItemsRegister.getAllItemsOfStock(stock.getId());

        for (Map.Entry<Item, Integer> entry : itemAndQuantity.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            for (StockItems stockItem : stockItemsList) {
                if (stockItem.getItemId() == item.getId() && stockItem.getItemsQuantity() < quantity)
                    return false;
            }
        }
        return true;
    }

    public boolean removeItemsFromPharmacyStock(Map<Item, Integer> itemAndQuantity, String pharmacyNif) {
        int itemId;
        int stockId;
        int itemQuantity;
        int newItemQuantity;
        boolean removeCheck;
        boolean addCheck;
        ItemsOfOrder aux;
        for (Item itm : itemAndQuantity.keySet()) {
            itemId = itm.getId();
            stockId = stockRegister.getStockByPharmacyNif(pharmacyNif).getId();
            itemQuantity = itemAndQuantity.get(itm);
            newItemQuantity = stockItemsRegister.getStockItem(itemId, stockId).getItemsQuantity() - itemQuantity;
            removeCheck = stockItemsRegister.removeStockItems(itemId, stockId);
            if (!removeCheck)
                return false;
            addCheck = stockItemsRegister.addStockItems(itemId, stockId, newItemQuantity);
            if (!addCheck)
                return false;
        }
        return true;
    }

    public Receipt makeReceipt(int receiptId) {

        double shippingPrice = Delivery.getShippingCosts();
        double totalPrice = this.order.getItemsPrice() + shippingPrice;

        this.orderRegister.updateOrder(order.getId(), "Waiting Delivery", order.getItemsPrice(), order.getClientNif());

        Credits credits = platform.getCreditsRegister().getCreditsByClientNif(order.getClientNif());
        if (credits.getNumberOfCredits() >= 10) {
            shippingPrice = 0;
            platform.getCreditsRegister().updateCredits(credits.getId(), credits.getNumberOfCredits() - 10, credits.getClientNif());
        }

        boolean receipt = platform.getReceiptRegister().addReceipt(receiptId, totalPrice, order.getId(), client.getNif(), pharmacy.getNif());

        if (receipt) {
            Receipt r = new Receipt(receiptId, totalPrice, order.getId(), client.getNif(), pharmacy.getNif());
            EmailSystem.sendEmail(client.getEmailAddress(), "RECEIPT", r.toString());
            return r;
        }
        return null;

    }


    public boolean makeDelivery(double shippingPrice) {
        return platform.getDeliveryRegister().addDelivery(order.getId(), "processed", shippingPrice);
    }

    public Map<String, Map<Item, Integer>> getPharmaciesWithItemsOnStock(Map<Item, Integer> itemsNeeded, Pharmacy pharmacy) {

        Map<String, Map<Item, Integer>> itemsPerPharmacy = new HashMap<>();
        List<Pharmacy> reachablePharmacies = new ArrayList<>();

        //All pharmacies on database
        List<Pharmacy> pharmacies = platform.getPharmacyRegister().getAllPharmacies();

        //All paths between all pharmacies
        List<LandPath> pathsBetweenPharmacies = platform.getLandPathRegister().allPathsBetweenPharmacies();

        DeliverySimulationsController deliverySimulationsController = new DeliverySimulationsController();

        //One scooter of pharmacy with full battery , null if does not exists!
        Scooter betterScooter = platform.getScooterRegister().getBetterScooterToDelivery(pharmacy.getNif());

        //random pharmacy courier
        List<Courier> pharmacyCouriers = platform.getCourierRegister().getPharmacyCouriers(pharmacy.getNif());

        //If no scooters are available or no couriers are available, it's not possible a run to other pharmacy, return null
        if (betterScooter == null || pharmacyCouriers.isEmpty()) {
            return null;
        }

        Courier courier = pharmacyCouriers.get(0);

        EnergyLandMapController energyLandMapController = deliverySimulationsController.getEnergyOnAShortLandPath(pathsBetweenPharmacies, betterScooter, courier);

        //Address of origin pharmacy
        Address originAddress = platform.getAddressRegister().getAddressByID(pharmacy.getAddressId());

        //Scooter energy
        double scooterEnergy = betterScooter.getCurrentBattery();

        //Check all reachable pharmacies
        for (Pharmacy phar : pharmacies) {
            //destination address
            Address pharAddress = platform.getAddressRegister().getAddressByID(phar.getAddressId());
            //energy needed to perform a run and come back
            double energyNeeded = energyLandMapController.getMostEfficientRoute(originAddress, pharAddress, new LinkedList<>())
                    + energyLandMapController.getMostEfficientRoute(pharAddress, originAddress, new LinkedList<>());

            //check if the pharmacy is reachable
            if (scooterEnergy >= energyNeeded) {
                reachablePharmacies.add(phar);
            }
        }

        // auxiliar hashmap to add item and quantity
        HashMap<Item, Integer> aux = new HashMap<>();
        Map<Item, Integer> auxItems;

        // check stock of reachable pharmacies
        for (Pharmacy phar : reachablePharmacies) {
            auxItems = new HashMap<>();

            Stock stock = platform.getStockRegister().getStockByPharmacyNif(phar.getNif());
            List<StockItems> stockItems = platform.getStockItemsRegister().getAllItemsOfStock(stock.getId());

            //check if the stock has the items and quantity needed
            for (Map.Entry me : itemsNeeded.entrySet()) {
                for (StockItems si : stockItems) {
                    Item item = (Item) me.getKey();
                    int quantity = (int) me.getValue();

                    if (item.getId() == si.getItemId() && quantity >= si.getItemsQuantity()) {
                        aux.put(item, quantity);
                        itemsPerPharmacy.put(phar.getNif(), aux);
                    }
                }
            }

            for (String ph : itemsPerPharmacy.keySet()) {
                for (Item itm : itemsPerPharmacy.get(ph).keySet()) {
                    auxItems.put(itm, itemsPerPharmacy.get(ph).get(itm));
                }
            }

            //when the size of itemsPerPharmacy be the same of itemsNeeded, all items needed were found on other pharmacies
            if (itemsPerPharmacy.size() == itemsNeeded.size()) {
                return itemsPerPharmacy;
            }
        }

        // return the list of items per pharmacy even that the items were not found
        return itemsPerPharmacy;
    }


/*
    public Map<Item, Integer> removeUnavailableItems(Map<String, Map<Item, Integer>> itemsPerPharmacy) {
        Map<Item, Integer> auxItems = new HashMap<>();

        for (String pharmacy : itemsPerPharmacy.keySet()) {
            for (Item item : itemsPerPharmacy.get(pharmacy).keySet()) {
                auxItems.put(item, itemsPerPharmacy.get(pharmacy).get(item));
            }
        }

        return auxItems;
    }
*/

    public Pharmacy getNearestPharmacy() {
        Pharmacy nearestPharmacy = null;

        //All pharmacies on database
        List<Pharmacy> allPharmacies = platform.getPharmacyRegister().getAllPharmacies();

        //All paths between all pharmacies and clients
        List<LandPath> pathsBetweenClientsAndPharmacies = platform.getLandPathRegister().getAllPathsBetweenClientsAndPharmacies();

        DeliverySimulationsController deliverySimulationsController = new DeliverySimulationsController();
        DistanceLandMapController distanceLandMapController = deliverySimulationsController.getDistanceLandMap(pathsBetweenClientsAndPharmacies);

        double shortesthDistance = Double.MAX_VALUE;
        for (Pharmacy phar : allPharmacies) {
            Address pharmacyAddress = platform.getAddressRegister().getAddressByID(phar.getAddressId());
            double distance = distanceLandMapController.getShortestRoute(pharmacyAddress, platform.getAddressRegister().getAddressByID(client.getAddressId()), new LinkedList<>());
            if (distance < shortesthDistance) {
                shortesthDistance = distance;
                nearestPharmacy = phar;
            }
        }
        return nearestPharmacy;
    }

}

