package lapr.project.model.DomainClasses;

import java.util.Objects;

public class StockItems {

    private final int itemId;
    private final int stockId;
    private final int itemsQuantity;

    public StockItems(int itemId, int stockId, int quantity) {
        this.itemId = itemId;
        this.stockId = stockId;
        this.itemsQuantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStockId() {
        return stockId;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockItems)) return false;
        StockItems that = (StockItems) o;
        return itemId == that.itemId && stockId == that.stockId && itemsQuantity == that.itemsQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, stockId, itemsQuantity);
    }

    @Override
    public String toString() {
        return String.format("StockItems->ItemId:%s,StockId:%s,ItemsQuantity:%s",itemId,stockId,itemsQuantity);
    }
}
