package lapr.project.data;

import lapr.project.model.DomainClasses.Drone;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DroneDB extends DataHandler {

    public Drone getDrone(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDrone(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int droneId = rSet.getInt(1);
                Double droneBatterySize = rSet.getDouble(2);
                Double droneBatteryConsumption = rSet.getDouble(3);
                Double dronebattteryEfficiency = rSet.getDouble(4);
                Double droneCurrentBattery = rSet.getDouble(5);
                Double droneWeight = rSet.getDouble(6);
                Double droneFrontalArea = rSet.getDouble(7);
                Double droneMotorPower = rSet.getDouble(8);
                String pharmacyNif = rSet.getString(9);
                Double dronePayload = rSet.getDouble(10);

                return new Drone(droneId, droneBatterySize, droneBatteryConsumption, dronebattteryEfficiency, droneCurrentBattery, droneWeight, droneFrontalArea, droneMotorPower, pharmacyNif, dronePayload);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Drone with ID:" + id);
    }

    public Boolean addDrone(Drone drone) {
        return addDrone(drone.getId(), drone.getBatterySize(), drone.getBatteryConsumption(), drone.getBatteryEfficiency(), drone.getCurrentBattery(), drone.getWeight(), drone.getFrontalArea(), drone.getMotorPower(), drone.getPharmacyNif(), drone.getPayload());
    }

    private Boolean addDrone(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, double payload) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addDrone(?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setDouble(2, batterySize);
            callStmt.setDouble(3, batteryConsumption);
            callStmt.setDouble(4, batteryEfficiency);
            callStmt.setDouble(5, currentBattery);
            callStmt.setDouble(6, weight);
            callStmt.setDouble(7, frontalArea);
            callStmt.setDouble(8, motorPower);
            callStmt.setString(9, pharmacyNif);
            callStmt.setDouble(10, payload);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }

    }

    public Boolean removeDrone(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");

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
