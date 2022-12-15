package lapr.project.data;

import lapr.project.model.DomainClasses.Client;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDB extends DataHandler {

    public Client getClient(String nif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClient(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, nif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String clientNIF = rSet.getString(1);
                String clientEmailAddress = rSet.getString(2);
                String clientName = rSet.getString(3);
                int clientAddressId = rSet.getInt(4);

                return new Client(clientNIF, clientEmailAddress, clientName, clientAddressId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Scooter with ID:" + nif);
    }

    public boolean addClient(Client client) {
        return addClient(client.getNif(), client.getEmailAddress(), client.getName(), client.getAddressId());
    }

    private boolean addClient(String nif, String emailAddress, String name, int addressId) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?) }");

            callStmt.setString(1, nif);
            callStmt.setString(2, emailAddress);
            callStmt.setString(3, name);
            callStmt.setInt(4, addressId);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


    public boolean removeClient(String nif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeClient(?) }");

            callStmt.setString(1, nif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


    public Client getClientByEmail(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientByEmail(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String clientNIF = rSet.getString(1);
                String clientEmailAddress = rSet.getString(2);
                String clientName = rSet.getString(3);
                int clientAddressId = rSet.getInt(4);

                return new Client(clientNIF, clientEmailAddress, clientName, clientAddressId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Client with email:" + email);
    }
}
