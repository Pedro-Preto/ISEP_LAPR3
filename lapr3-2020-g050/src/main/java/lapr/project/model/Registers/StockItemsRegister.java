package lapr.project.model.Registers;

import lapr.project.data.StockItemsDB;
import lapr.project.model.DomainClasses.StockItems;

import java.util.List;

public class StockItemsRegister {

    private final StockItemsDB stockItemsDB;

    public StockItemsRegister(){
        this.stockItemsDB = new StockItemsDB();
    }

    public boolean addStockItems(int itemId, int stockId, int itemsQuantity){
        StockItems stockItems = new StockItems(itemId, stockId, itemsQuantity);
        return stockItemsDB.addStockItems(stockItems);
    }

    public boolean removeStockItems(int itemId, int stockId) {
        return stockItemsDB.removeStockItems(itemId, stockId);
    }

    public StockItems getStockItem(int itemId, int stockId){
        return stockItemsDB.getStockItems(itemId, stockId);
    }

    public List<StockItems> getAllItemsOfStock(int stockId) { return stockItemsDB.getAllItemsOfStock(stockId); }

}
