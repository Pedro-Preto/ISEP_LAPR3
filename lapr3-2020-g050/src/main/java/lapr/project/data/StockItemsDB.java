package lapr.project.data;

import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.StockItems;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockItemsDB extends DataHandler {

    public StockItems getStockItems(int itemId, int stockId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStockItem(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, itemId);
            callStmt.setInt(3, stockId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int stockItemsItemId = rSet.getInt(1);
                int stockItemsStockId = rSet.getInt(2);
                int stockItemsQuantity = rSet.getInt(3);

                return new StockItems(stockItemsItemId, stockItemsStockId, stockItemsQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No StockItem with the combination of IDs:" + itemId + ", " + stockId);
    }

    public List<StockItems> getAllItemsOfStock(int stockId) {
        List<StockItems> itemList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{?= call getAllItemsOfStock(?)}");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, stockId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {
                itemList.add(new StockItems(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return itemList;
    }

    public boolean addStockItems(StockItems stockItems) {
        return addStockItems(stockItems.getItemId(), stockItems.getStockId(), stockItems.getItemsQuantity());
    }

    private boolean addStockItems(int itemId, int stockId, int itemsQuantity) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addStockItem(?,?,?) }");

            callStmt.setInt(1, itemId);
            callStmt.setInt(2, stockId);
            callStmt.setInt(3, itemsQuantity);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            closeAll();
        }
    }

    public boolean removeStockItems(int itemId, int stockId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call removeStockItem(?,?) }");

            callStmt.setInt(1, itemId);
            callStmt.setInt(2, stockId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            closeAll();
        }
    }
}
