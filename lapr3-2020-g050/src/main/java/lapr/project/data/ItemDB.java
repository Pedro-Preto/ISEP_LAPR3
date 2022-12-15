package lapr.project.data;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.StockItems;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemDB extends DataHandler {

    public Item getItem(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getItem(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int itemId = rSet.getInt(1);
                String itemName = rSet.getString(2);
                Double itemPrice = rSet.getDouble(3);
                Double itemWeight = rSet.getDouble(4);

                return new Item(itemId, itemName, itemPrice, itemWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Item with ID:" + id);
    }

    public Item getItemByName(String name) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getItemByName(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, name);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int itemId = rSet.getInt(1);
                String itemName = rSet.getString(2);
                Double itemPrice = rSet.getDouble(3);
                Double itemWeight = rSet.getDouble(4);

                return new Item(itemId, itemName, itemPrice, itemWeight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Item with name: " + name);
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllItems() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                itemList.add(new Item(rSet.getInt(1), rSet.getString(2), rSet.getDouble(3), rSet.getDouble(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return itemList;
    }

    public boolean addItem(Item item) {
        return addItem(item.getId(),item.getName(),item.getPrice(),item.getWeight());
    }

    private boolean addItem(int id, String name, Double price, Double weight) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addItem(?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, name);
            callStmt.setDouble(3, price);
            callStmt.setDouble(4, weight);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeItem(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeItem(?) }");

            callStmt.setInt(1, id);

            callStmt.execute();

            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
