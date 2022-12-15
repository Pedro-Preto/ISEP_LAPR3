package lapr.project.controller;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.Registers.StockItemsRegister;

import java.util.Map;
import java.util.TreeMap;


public class AddItemsToPharmacyStockController {

    private final StockItemsRegister stockItemRegister;

    public AddItemsToPharmacyStockController() {
        ApplicationEM app = ApplicationEM.getInstance();
        Platform platform = app.getPlatform();
        this.stockItemRegister = platform.getStockItemsRegister();
    }

    public boolean addItemsToStock(int itemId, int stockId, int itemsQuantity) {
        return stockItemRegister.addStockItems(itemId, stockId, itemsQuantity);
    }

    public Boolean updateStock(Map<Item, Integer> itemMap, int stockId, int idItem, int itemQuantity) {
        Boolean a = null;
        for (Item item : itemMap.keySet()) {
            if (item.getId() == idItem) {
                stockItemRegister.removeStockItems(idItem, stockId);
                a = stockItemRegister.addStockItems(idItem, stockId,itemQuantity);
            }
        }
        return a;
    }

}
