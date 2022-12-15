package lapr.project.data;

import lapr.project.model.DomainClasses.Courier;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDB extends DataHandler {

    public Courier getCourier(String nif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, nif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String courierNIF = rSet.getString(1);
                String courierEmailAddress = rSet.getString(2);
                String courierName = rSet.getString(3);
                String courierIBAN = rSet.getString(4);
                String courierCellPhone = rSet.getString(5);
                double courierWeight = rSet.getDouble(6);
                String courierPharmacyNIF = rSet.getString(7);
                double courierPayload = rSet.getDouble(8);

                return new Courier(courierNIF, courierName, courierEmailAddress, courierCellPhone, courierIBAN, courierWeight, courierPayload, courierPharmacyNIF);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + nif);
    }

    public List<Courier> getPharmacyCouriers(String pharmacyNif) {
        CallableStatement callStmt = null;
        List<Courier> couriers = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyCouriers(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, pharmacyNif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String courierNIF = rSet.getString(1);
                String courierEmailAddress = rSet.getString(2);
                String courierName = rSet.getString(3);
                String courierIBAN = rSet.getString(4);
                String courierCellPhone = rSet.getString(5);
                double courierWeight = rSet.getDouble(6);
                String courierPharmacyNIF = rSet.getString(7);
                double courierPayload = rSet.getDouble(8);

                couriers.add(new Courier(courierNIF, courierName, courierEmailAddress, courierCellPhone, courierIBAN, courierWeight, courierPayload, courierPharmacyNIF));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return couriers;
    }

    public boolean addCourier(Courier courier) {
        return addCourier(courier.getNif(), courier.getEmailAddress(), courier.getName(), courier.getIban(), courier.getCellPhone(), courier.getWeight(), courier.getPharmacyNif(), courier.getPayload());
    }

    private boolean addCourier(String nif, String emailAddress, String name, String iban, String cellPhone, double weight, String pharmacyNif, double payload) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, nif);
            callStmt.setString(2, emailAddress);
            callStmt.setString(3, name);
            callStmt.setString(4, iban);
            callStmt.setString(5, cellPhone);
            callStmt.setDouble(6, weight);
            callStmt.setString(7, pharmacyNif);
            callStmt.setDouble(8, payload);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


    public boolean removeCourier(String nif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeCourier(?) }");

            callStmt.setString(1, nif);

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
