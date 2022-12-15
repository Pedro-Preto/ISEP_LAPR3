package lapr.project.data;

import lapr.project.model.DomainClasses.ChargingStation;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChargingStationDB extends DataHandler {

    public ChargingStation getChargingStation(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getChargingStation(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int chargingStationParkSlotId = rSet.getInt(1);
                double chargingStationPower = rSet.getDouble(2);
                double chargingStationCapacity = rSet.getDouble(3);


                return new ChargingStation(chargingStationParkSlotId, chargingStationPower, chargingStationCapacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Charging Station with ID:" + id);
    }

    public Boolean addChargingStation(ChargingStation chargingStation) {
        return addChargingStation(chargingStation.getParkSlotId(), chargingStation.getPower(), chargingStation.getCapacity());
    }

    private Boolean addChargingStation(int parkSlotId, double power, double capacity) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addChargingStation(?,?,?) }");

            callStmt.setInt(1, parkSlotId);
            callStmt.setDouble(2, power);
            callStmt.setDouble(3, capacity);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public Boolean removeChargingStation(int parkSlotId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeChargingStation(?) }");

            callStmt.setInt(1, parkSlotId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public List<ChargingStation> getOccupiedChargingStationsOfPark(int parkId){
        List<ChargingStation> chargingStationList = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOccupiedChargingStationsOfPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, parkId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                chargingStationList.add(new ChargingStation(rSet.getInt(1), rSet.getDouble(2), rSet.getDouble(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return chargingStationList;
    }
}
