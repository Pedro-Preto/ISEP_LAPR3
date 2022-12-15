package lapr.project.data;

import lapr.project.model.DomainClasses.GeoLocation;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeoLocationDB extends DataHandler {

    public GeoLocation getGeoLocation(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getGeoLocation(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int geoLocationId = rSet.getInt(1);
                Double geoLocationLatitude = rSet.getDouble(2);
                Double geoLocationLongitude = rSet.getDouble(3);
                Integer geoLocationAltitude = rSet.getInt(4);
                int addressId = rSet.getInt(5);

                return new GeoLocation(geoLocationId, geoLocationLatitude, geoLocationLongitude, geoLocationAltitude, addressId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No GeoLocation with ID:" + id);
    }

    public GeoLocation getGeoLocationByAddresID(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getGeoLocationByAddressID(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int geoLocationId = rSet.getInt(1);
                Double geoLocationLatitude = rSet.getDouble(2);
                Double geoLocationLongitude = rSet.getDouble(3);
                Integer geoLocationAltitude = rSet.getInt(4);
                int addressId = rSet.getInt(5);

                return new GeoLocation(geoLocationId, geoLocationLatitude, geoLocationLongitude, geoLocationAltitude, addressId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeAll();
        }
        throw new IllegalArgumentException("No GeoLocation with ID:" + id);
    }

    public boolean addGeoLocation(GeoLocation geoLocation) {
        return addGeoLocation(geoLocation.getId(), geoLocation.getLongitude(), geoLocation.getLatitude(), geoLocation.getAltitude(), geoLocation.getAddressId());
    }

    private boolean addGeoLocation(int id, Double longitude, Double latitude, int altitude, int addressId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addGeoLocation(?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setDouble(2, latitude);
            callStmt.setDouble(3, longitude);
            callStmt.setInt(4,altitude);
            callStmt.setInt(5,addressId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeGeoLocation(int gid) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeGeoLocation(?) }");

            callStmt.setInt(1, gid);

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
