package lapr.project.data;

import lapr.project.model.DomainClasses.ParkSlot;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkSlotDB extends DataHandler {

    public ParkSlot getParkSlot(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkSlot(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int parkSlotId = rSet.getInt(1);
                int parkSlotParkId = rSet.getInt(2);
                int parkSlotVehicleId = rSet.getInt(3);

                return new ParkSlot(parkSlotId, parkSlotParkId, parkSlotVehicleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No ParkSlot with ID:" + id);
    }

    public Boolean addParkSlot(ParkSlot parkSlot) {
        return addParkSlot(parkSlot.getId(), parkSlot.getParkId());
    }

    private Boolean addParkSlot(int id, int parkId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addParkSlot(?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, parkId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public Boolean removeParkSlot(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeParkSlot(?) }");

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

    public Boolean updateParkSlot(int id, Integer vehicleID) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call updateParkSlot(?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2, vehicleID);

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
