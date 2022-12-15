package lapr.project.data;

import lapr.project.model.DomainClasses.DronePark;
import lapr.project.model.DomainClasses.ScooterPark;
import lapr.project.model.DomainClasses.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class ScooterParkDB extends DataHandler {

    public ScooterPark getScooterPark(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooterPark(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int scooterParkId = rSet.getInt(1);
                String scooterParkpharmacyNIF = rSet.getString(2);
                double power = rSet.getDouble(3);

                return new ScooterPark(scooterParkId, scooterParkpharmacyNIF, power);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No ScooterPark with ID:" + id);
    }

    public Boolean addScooterPark(ScooterPark scooterPark) {
        return addScooterPark(scooterPark.getId(), scooterPark.getPharmacyNIF(), scooterPark.getPower());
    }

    private Boolean addScooterPark(int id, String pharmacyNIF, double power) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addScooterPark(?,?,?) }");

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

    public Boolean removeScooterPark(int pid) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooterPark(?) }");

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

    public List<Scooter> getScootertParkedOnPharmacy(String pharNIF) {
        List<Scooter> pharParkedScooters = new ArrayList<>();

        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getScootertParkedOnPharmacy(?)}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, pharNIF);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                pharParkedScooters.add(new Scooter(rs.getInt(1), rs.getDouble(2), rs.getDouble(3),
                        rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7),
                        rs.getDouble(8), rs.getString(9), rs.getString(10)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeAll();
        }
        return pharParkedScooters;


    }
}
