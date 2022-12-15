package lapr.project.data;

import lapr.project.model.DomainClasses.Address;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDB extends DataHandler {

    public Address getAddress(int id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAddress(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int addressId = rSet.getInt(1);
                String addressStreet = rSet.getString(2);
                String addressDoorNumber = rSet.getString(3);
                String addressCity = rSet.getString(4);
                String addressCountry = rSet.getString(5);

                return new Address(addressId, addressStreet, addressDoorNumber, addressCity, addressCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Address with ID:" + id);
    }

    public boolean addAddress(Address address) {
        return addAddress(address.getId(), address.getStreet(), address.getDoorNumber(), address.getCity(), address.getCountry());
    }

    private boolean addAddress(int aid, String astr, String adorn, String acity, String acountry) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addAddress(?,?,?,?,?) }");

            callStmt.setInt(1, aid);
            callStmt.setString(2, astr);
            callStmt.setString(3, adorn);
            callStmt.setString(4, acity);
            callStmt.setString(5, acountry);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }


        public boolean removeAddress ( int pid){
            try {

                CallableStatement callStmt = getConnection().prepareCall("{ call removeAddress(?) }");

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
    }
