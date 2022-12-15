package lapr.project.data;

import lapr.project.model.DomainClasses.LandPath;
import lapr.project.model.DomainClasses.Receipt;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandPathDB extends DataHandler {

    public LandPath getLandPath(int startId, int endId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLandPath(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, startId);
            callStmt.setInt(3, endId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int landPathStartId = rSet.getInt(1);
                int landPathEndId = rSet.getInt(2);
                Double landPathDistance = rSet.getDouble(3);
                Double landPathWindDirection = rSet.getDouble(4);
                Double landPathWindVelocity = rSet.getDouble(5);
                Double landPathRoadResistanceCoefficient = rSet.getDouble(6);
                Double landPathElevation = rSet.getDouble(7);

                return new LandPath(landPathStartId, landPathEndId, landPathDistance, landPathWindDirection, landPathWindVelocity, landPathRoadResistanceCoefficient, landPathElevation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No LandPath with startId, endId: " + startId + ", " + endId);
    }

    public List<LandPath> allPathsBetweenPharmacies() {
        CallableStatement callStmt = null;
        List<LandPath> paths = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLandPath(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int landPathStartId = rSet.getInt(1);
                int landPathEndId = rSet.getInt(2);
                Double landPathDistance = rSet.getDouble(3);
                Double landPathWindDirection = rSet.getDouble(4);
                Double landPathWindVelocity = rSet.getDouble(5);
                Double landPathRoadResistanceCoefficient = rSet.getDouble(6);
                Double landPathElevation = rSet.getDouble(7);

                paths.add(new LandPath(landPathStartId, landPathEndId, landPathDistance, landPathWindDirection, landPathWindVelocity, landPathRoadResistanceCoefficient, landPathElevation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return paths;
    }

    public boolean addLandPath(LandPath landPath) {
        return addLandPath(landPath.getGeoLocationStart(), landPath.getGeoLocationEnd(), landPath.getDistance(), landPath.getWindDirection(), landPath.getWindVelocity(), landPath.getRoadResistanceCoefficient(), landPath.getInclination());
    }

    private boolean addLandPath(int geoLocationStart, int geoLocationEnd, Double distance, Double windDirection, Double windVelocity, Double roadResistanceCoefficient, Double elevation) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addLandPath(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, geoLocationStart);
            callStmt.setInt(2, geoLocationEnd);
            callStmt.setDouble(3, distance);
            callStmt.setDouble(4, windDirection);
            callStmt.setDouble(5, windVelocity);
            callStmt.setDouble(6, roadResistanceCoefficient);
            callStmt.setDouble(7, elevation);


            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }

    }

    public boolean removeLandPath(int startId, int endId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeLandPath(?,?) }");

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

    public List<LandPath> getAllPathsBetweenClientsAndPharmacies() {
        List<LandPath> paths = new ArrayList<>();


        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getAllPathsBetweenPharmaciesAndClients()}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                paths.add(new LandPath(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
                        rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return paths;
    }
}
