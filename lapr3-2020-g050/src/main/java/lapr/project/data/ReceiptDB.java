package lapr.project.data;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptDB extends DataHandler {

    public Receipt getReceipt(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getReceipt(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int receiptId = rSet.getInt(1);
                double totalPrice = rSet.getDouble(2);
                int orderId = rSet.getInt(3);
                String clientNif = rSet.getString(4);
                String pharmacyNif = rSet.getString(5);


                return new Receipt(receiptId, totalPrice, orderId, clientNif, pharmacyNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Receipt with ID:" + id);
    }

    public boolean addReceipt(Receipt receipt) {
        return addReceipt(receipt.getId(), receipt.getTotalPrice(), receipt.getOrderId(), receipt.getClientNif(), receipt.getPharmacyNif());
    }

    private boolean addReceipt(int id, double totalPrice, int orderId, String clientNif, String pharmacyNif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addReceipt(?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setDouble(2, totalPrice);
            callStmt.setInt(3, orderId);
            callStmt.setString(4, clientNif);
            callStmt.setString(5, pharmacyNif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeReceipt(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeReceipt(?) }");

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

    public List<Receipt> getReceiptByPharmacyNif(String pharNIF) {
        List<Receipt> ordersReceipt = new ArrayList<>();

        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getReceiptByPharmacyNif(?)}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, pharNIF);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                ordersReceipt.add(new Receipt(rs.getInt(1), rs.getDouble(2), rs.getInt(3),
                        rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return ordersReceipt;

    }
    public List<Receipt> getReceiptByClientNif(String clientNIF) {
        List<Receipt> ordersReceipt = new ArrayList<>();

        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getReceiptByClientNif(?)}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, clientNIF);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                ordersReceipt.add(new Receipt(rs.getInt(1), rs.getDouble(2), rs.getInt(3),
                        rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return ordersReceipt;

    }
}
