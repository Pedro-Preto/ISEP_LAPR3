package lapr.project.data;

import lapr.project.model.DomainClasses.Credits;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditsDB extends DataHandler {

    public Credits getCredits(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCredits(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int creditsId = rSet.getInt(1);
                int creditsNumber = rSet.getInt(2);
                String clientNif = rSet.getString(3);

                return new Credits(creditsId, creditsNumber, clientNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Credits with ID:" + id);
    }

    public Credits getCreditsByClientNif(String nif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCreditsByClientNif(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, nif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int creditsId = rSet.getInt(1);
                int creditsNumber = rSet.getInt(2);
                String clientNif = rSet.getString(3);

                return new Credits(creditsId, creditsNumber, clientNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Credits with clientNif: " + nif);
    }

    public boolean addCredits(Credits credits) {
        return addCredits(credits.getId(), credits.getNumberOfCredits(), credits.getClientNif());
    }

    public boolean addCredits(int cid, int cnumber, String clientNif) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addCredits(?,?,?) }");

            callStmt.setInt(1, cid);
            callStmt.setInt(2, cnumber);
            callStmt.setString(3, clientNif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


    public boolean removeCredits(int cid) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeCredits(?) }");

            callStmt.setInt(1, cid);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean updateCredits(int cid, int cnumber, String clientNif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call updateCredits(?,?,?) }");

            callStmt.setInt(1, cid);
            callStmt.setInt(2, cnumber);
            callStmt.setString(3, clientNif);

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