package lapr.project.data;

import lapr.project.model.DomainClasses.PharmacyAdministrator;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyAdministratorDB extends DataHandler {

    public PharmacyAdministrator getPharmacyAdministrator(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyAdministrator(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String pharmacyAdministratorEmail = rSet.getString(1);
                String pharmacyAdministratorName = rSet.getString(2);
                String pharmacyAdministratorPharmacyNif = rSet.getString(3);

                return new PharmacyAdministrator(pharmacyAdministratorEmail, pharmacyAdministratorName, pharmacyAdministratorPharmacyNif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No PharmacyAdministrator with ID:" + email);
    }

    public boolean addPharmacyAdministrator(PharmacyAdministrator pharmacyAdministrator) {
        return addPharmacyAdministrator(pharmacyAdministrator.getEmailAddress(), pharmacyAdministrator.getName(), pharmacyAdministrator.getPharmacyNif());
    }

    private boolean addPharmacyAdministrator(String emailAddress, String name, String pharmacyNif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacyAdministrator(?,?,?) }");

            callStmt.setString(1, emailAddress);
            callStmt.setString(2, name);
            callStmt.setString(3, pharmacyNif);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removePharmacyAdministrator(String emailAddress) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacyAdministrator(?) }");

            callStmt.setString(1, emailAddress);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public List<PharmacyAdministrator> getPharmacyAdministratorByPharmacyNif(String pharmacyNif){
        CallableStatement callStmt = null;
        List<PharmacyAdministrator> pharmacyAdministrators = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyAdministrator(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, pharmacyNif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String pharmacyAdministratorEmail = rSet.getString(1);
                String pharmacyAdministratorName = rSet.getString(2);
                String pharmacyAdministratorPharmacyNif = rSet.getString(3);

                pharmacyAdministrators.add(new PharmacyAdministrator(pharmacyAdministratorEmail, pharmacyAdministratorName, pharmacyAdministratorPharmacyNif));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return pharmacyAdministrators;
    }
}
