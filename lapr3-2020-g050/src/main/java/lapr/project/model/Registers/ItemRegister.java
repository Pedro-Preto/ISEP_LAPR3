package lapr.project.model.Registers;

import lapr.project.data.ItemDB;
import lapr.project.model.DomainClasses.Item;

import java.util.List;

public class ItemRegister {

    private final ItemDB itemDB;

    public ItemRegister() {
        this.itemDB = new ItemDB();
    }

    public boolean addItem(int id, String name, Double price, Double weight) {
        Item item = new Item(id, name, price, weight);
        return itemDB.addItem(item);
    }

    public boolean removeItem(int itemId) {
        return itemDB.removeItem(itemId);
    }

    public Item getItem(int itemId) {
        return itemDB.getItem(itemId);
    }

    public List<Item> getAllItems() {
        return itemDB.getAllItems();
    }

    public Item getItemByName(String name) {
        return itemDB.getItemByName(name);
    }
}
