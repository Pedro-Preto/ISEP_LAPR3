package lapr.project.data;

import lapr.project.model.DomainClasses.DronePark;
import lapr.project.model.DomainClasses.Park;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DroneParkDB extends DataHandler {

    public DronePark getDronePark(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDronePark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int droneParkId = rSet.getInt(1);
                String droneParkpharmacyNIF = rSet.getString(2);
                double power = rSet.getDouble(3);

                return new DronePark(droneParkId, droneParkpharmacyNIF, power);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No DronePark with ID:" + id);
    }

    public Boolean addDronePark(DronePark dronePark) {
        return addDronePark(dronePark.getId(), dronePark.getPharmacyNIF(), dronePark.getPower());
    }

    private Boolean addDronePark(int id, String pharmacyNIF, double power) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addDronePark(?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, pharmacyNIF);
            callStmt.setDouble(3, power);


            callStmt.execute();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public Boolean removeDronePark(int pid) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeDronePark(?) }");

            callStmt.setInt(1, pid);

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
