package lapr.project.data;

import lapr.project.model.DomainClasses.Item;
import lapr.project.model.DomainClasses.Stock;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockDB extends DataHandler{


    public Stock getStock(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStock(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int stockId = rSet.getInt(1);
                String pharmacyNif = rSet.getString(2);

                return new Stock(stockId, pharmacyNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Stock with ID:" + id);
    }

    public Stock getStockByPharmacyNif(String nif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStockByPharmacyNif(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, nif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int stockId = rSet.getInt(1);
                String pharmacyNif = rSet.getString(2);

                return new Stock(stockId, pharmacyNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Stock with pharmacyNif:" + nif);
    }

    public List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllStocks() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                stockList.add(new Stock(rSet.getInt(1), rSet.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return stockList;
    }

    public boolean addStock(Stock stock) {
        return addStock(stock.getId(), stock.getPharmacyNif());
    }

    private boolean addStock(int id, String nif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addStock(?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, nif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeStock(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeStock(?) }");

            callStmt.setInt(1, id);

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
