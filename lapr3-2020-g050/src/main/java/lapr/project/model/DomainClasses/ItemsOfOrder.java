package lapr.project.model.DomainClasses;

import java.util.Objects;

public class ItemsOfOrder {

    private final int itemId;
    private final int orderId;
    private final int itemsQuantity;

    public ItemsOfOrder(int itemId, int orderId, int itemsQuantity) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.itemsQuantity = itemsQuantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemsOfOrder)) return false;
        ItemsOfOrder that = (ItemsOfOrder) o;
        return itemId == that.itemId && orderId == that.orderId && itemsQuantity == that.itemsQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, orderId, itemsQuantity);
    }

    @Override
    public String toString() {
        return String.format("ItemsOfOrder->ItemId:%s,OrderId:%s,ItemsQuantity:%s",itemId,orderId,itemsQuantity);
    }
}
