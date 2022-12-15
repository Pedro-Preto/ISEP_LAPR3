package lapr.project.data;


import lapr.project.model.DomainClasses.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScooterDB extends DataHandler {

    public Scooter getScooter(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooter(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterId = rSet.getInt(1);
                Double scooterBatterySize = rSet.getDouble(2);
                Double scooterBatteryConsumption = rSet.getDouble(3);
                Double scooterbattteryEfficiency = rSet.getDouble(4);
                Double scooterCurrentBattery = rSet.getDouble(5);
                Double scooterWeight = rSet.getDouble(6);
                Double scooterFrontalArea = rSet.getDouble(7);
                Double scooterMotorPower = rSet.getDouble(8);
                String pharmacyNif = rSet.getString(9);
                String scooterQrCode = rSet.getString(10);

                return new Scooter(scooterId, scooterBatterySize, scooterBatteryConsumption, scooterbattteryEfficiency,scooterCurrentBattery, scooterWeight, scooterFrontalArea, scooterMotorPower, pharmacyNif, scooterQrCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + id);
    }

    public Scooter getBetterScooterToDelivery(String pharmacyNif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getBetterScooterToDelivery(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, pharmacyNif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterId = rSet.getInt(1);
                Double scooterBatterySize = rSet.getDouble(2);
                Double scooterBatteryConsumption = rSet.getDouble(3);
                Double scooterbattteryEfficiency = rSet.getDouble(4);
                Double scooterCurrentBattery = rSet.getDouble(5);
                Double scooterWeight = rSet.getDouble(6);
                Double scooterFrontalArea = rSet.getDouble(7);
                Double scooterMotorPower = rSet.getDouble(8);
                String scooterQrCode = rSet.getString(10);

                return new Scooter(scooterId, scooterBatterySize, scooterBatteryConsumption, scooterbattteryEfficiency,scooterCurrentBattery, scooterWeight, scooterFrontalArea, scooterMotorPower, pharmacyNif, scooterQrCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooters available");
    }

    public Scooter getScooterByQrCode(String qrCode) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooterByQrCode(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, qrCode);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterId = rSet.getInt(1);
                Double scooterBatterySize = rSet.getDouble(2);
                Double scooterBatteryConsumption = rSet.getDouble(3);
                Double scooterbattteryEfficiency = rSet.getDouble(4);
                Double scooterCurrentBattery = rSet.getDouble(5);
                Double scooterWeight = rSet.getDouble(6);
                Double scooterFrontalArea = rSet.getDouble(7);
                Double scooterMotorPower = rSet.getDouble(8);
                String pharmacyNif = rSet.getString(9);
                String scooterQrCode = rSet.getString(10);

                return new Scooter(scooterId, scooterBatterySize, scooterBatteryConsumption, scooterbattteryEfficiency,scooterCurrentBattery, scooterWeight, scooterFrontalArea, scooterMotorPower, pharmacyNif, scooterQrCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with qrCode:" + qrCode);
    }

    public boolean addScooter(Scooter scooter) {
        return addScooter(scooter.getId(), scooter.getBatterySize(), scooter.getBatteryEfficiency(), scooter.getBatteryConsumption(), scooter.getCurrentBattery(), scooter.getWeight(), scooter.getFrontalArea(), scooter.getMotorPower(), scooter.getPharmacyNif(), scooter.getQrCode());
    }

    private boolean addScooter(int id, double batterySize, double batteryConsumption, double batteryEfficiency, double currentBattery, double weight, double frontalArea, double motorPower, String pharmacyNif, String qrCode) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setDouble(2, batterySize);
            callStmt.setDouble(3, batteryConsumption);
            callStmt.setDouble(4, batteryEfficiency);
            callStmt.setDouble(5, currentBattery);
            callStmt.setDouble(6, weight);
            callStmt.setDouble(7, frontalArea);
            callStmt.setDouble(8, motorPower);
            callStmt.setString(9, pharmacyNif);
            callStmt.setString(10, qrCode);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    
    }

    public boolean removeScooter(int id) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

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
