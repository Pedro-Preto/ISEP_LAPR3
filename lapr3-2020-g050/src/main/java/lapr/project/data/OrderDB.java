package lapr.project.data;

import lapr.project.controller.ApplicationEM;
import lapr.project.model.DomainClasses.Client;
import lapr.project.model.DomainClasses.Order;
import lapr.project.model.DomainClasses.Receipt;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDB extends DataHandler {

    public Order getOrder(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrder(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int orderId = rSet.getInt(1);
                String orderStatus = rSet.getString(2);
                double orderItemsPrice = rSet.getDouble(3);
                String clientNif = rSet.getString(4);

                return new Order(orderId, orderStatus, orderItemsPrice, clientNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Order with ID:" + id);
    }

    public boolean addOrder(Order order) {
        return addOrder(order.getId(), order.getStatus(), order.getItemsPrice(), order.getClientNif());
    }

    private boolean addOrder(int id, String status, double itemsPrice, String clientNif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addOrder(?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, status);
            callStmt.setDouble(3, itemsPrice);
            callStmt.setString(4, clientNif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeOrder(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrder(?) }");

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

    public boolean updateOrder(int id, String status, double itemsPrice, String clientNif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call updateOrder(?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, status);
            callStmt.setDouble(3, itemsPrice);
            callStmt.setString(4, clientNif);

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
