package lapr.project.model.Registers;

import lapr.project.data.ItemsOfOrderDB;
import lapr.project.model.DomainClasses.ItemsOfOrder;

import java.util.List;


public class ItemsOfOrderRegister {

    ItemsOfOrderDB itemsOfOrderDB;

    public ItemsOfOrderRegister(){
        this.itemsOfOrderDB = new ItemsOfOrderDB();
    }

    public boolean addItemsOfOrder(int itemId, int orderId, int itemsQuantity){
        ItemsOfOrder itemsOfOrder = new ItemsOfOrder(itemId, orderId, itemsQuantity);
        return itemsOfOrderDB.addItemsOfOrder(itemsOfOrder);
    }

    public boolean removeItemsOfOrder(int itemId, int orderId) {
        return itemsOfOrderDB.removeItemsOfOrder(itemId, orderId);
    }

    public List<ItemsOfOrder> getAllItemsOfOrder(int orderId) {
        return itemsOfOrderDB.getAllItemsOfOrder(orderId);
    }

}
