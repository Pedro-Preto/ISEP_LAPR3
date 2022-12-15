package lapr.project.data;

import lapr.project.model.DomainClasses.ItemsOfOrder;
import lapr.project.model.DomainClasses.StockItems;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsOfOrderDB extends DataHandler{

    public StockItems getItemsOfOrder(int itemId, int orderId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getItemsOfOrder(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, itemId);
            callStmt.setInt(3, orderId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int ItemsOfItemId = rSet.getInt(1);
                int ItemsOfOrderId = rSet.getInt(2);
                int ItemsOfQuantity = rSet.getInt(3);

                return new StockItems(ItemsOfItemId, ItemsOfOrderId, ItemsOfQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeAll();
        }
        throw new IllegalArgumentException("No ItemsOfOrder with the combination of IDs:" + itemId + ", " + orderId);
    }

    public List<ItemsOfOrder> getAllItemsOfOrder(int orderId) {
        List<ItemsOfOrder> itemList = new ArrayList<>();

        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getAllItemsOfOrder(?)}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, orderId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {
                itemList.add(new ItemsOfOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return itemList;
    }


    public boolean addItemsOfOrder(ItemsOfOrder itemsOfOrder) {
        return addItemsOfOrder(itemsOfOrder.getItemId(), itemsOfOrder.getOrderId(), itemsOfOrder.getItemsQuantity());
    }

    private boolean addItemsOfOrder(int itemId, int orderId, int itemsQuantity) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addOrderItem(?,?,?) }");

            callStmt.setInt(1, itemId);
            callStmt.setInt(2, orderId);
            callStmt.setInt(3, itemsQuantity);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeItemsOfOrder(int itemId, int orderId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrderItem(?,?) }");

            callStmt.setInt(1, itemId);
            callStmt.setInt(2, orderId);

            callStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }
}
