package lapr.project.data;

import lapr.project.model.DomainClasses.AerialPath;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AerialPathDB extends DataHandler {

    public AerialPath getAerialPath(int startId, int endId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAerialPath(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, startId);
            callStmt.setInt(3, endId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int aerialPathStartId = rSet.getInt(1);
                int aerialPathEndId = rSet.getInt(2);
                Double aerialPathDistance = rSet.getDouble(3);
                Double aerialPathWindDirection = rSet.getDouble(4);
                Double aerialPathWindVelocity = rSet.getDouble(5);
                Double aerialPathAirDensity = rSet.getDouble(6);
                Double aerialPathAirDragCoefficient = rSet.getDouble(7);

                return new AerialPath(aerialPathStartId, aerialPathEndId, aerialPathDistance, aerialPathWindDirection, aerialPathWindVelocity, aerialPathAirDensity, aerialPathAirDragCoefficient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No AerialPath with startId, endId: " + startId + ", " + endId);
    }

    public boolean addAerialPath(AerialPath aerialPath) {
        return addAerialPath(aerialPath.getGeoLocationStart(), aerialPath.getGeoLocationEnd(), aerialPath.getDistance(), aerialPath.getWindDirection(), aerialPath.getWindVelocity(), aerialPath.getAirDensity(), aerialPath.getAirDragCoefficient());
    }

    private boolean addAerialPath(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity, Double airDensity, Double airDragCoefficient) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addAerialPath(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, geoLocationStart);
            callStmt.setInt(2, geoLocationEnd);
            callStmt.setDouble(3, distance);
            callStmt.setDouble(4, windDirection);
            callStmt.setDouble(5, windVelocity);
            callStmt.setDouble(6, airDensity);
            callStmt.setDouble(7, airDragCoefficient);


            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


    public boolean removeAerialPath(int startId, int endId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeAerialPath(?,?) }");

            callStmt.setInt(1, startId);
            callStmt.setInt(2, endId);

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
