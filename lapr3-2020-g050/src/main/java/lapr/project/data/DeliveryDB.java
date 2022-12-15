package lapr.project.data;

import lapr.project.model.DomainClasses.Delivery;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryDB extends DataHandler {


    public Delivery getDelivery(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDelivery(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int deliverOrderId = rSet.getInt(1);
                String deliverstatus = rSet.getString(2);
                Double delivershippingPrice = rSet.getDouble(3);
                String delivercourierNIF = rSet.getString(4);
                int deliveryDroneId = rSet.getInt(5);

                return new Delivery(deliverOrderId, deliverstatus, delivershippingPrice, delivercourierNIF, deliveryDroneId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Delivery with ID:" + id);
    }

    public boolean addDelivery(Delivery delivery) {
        return addDelivery(delivery.getOrderId(), delivery.getStatus(), delivery.getShippingPrice());
    }

    private boolean addDelivery(int orderId, String status, Double shippingPrice) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addDelivery(?,?,?) }");

            callStmt.setInt(1, orderId);
            callStmt.setString(2, status);
            callStmt.setDouble(3, shippingPrice);

            callStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }

    }


    public boolean removeDelivery(int orderId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeDelivery(?) }");

            callStmt.setInt(1, orderId);

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
