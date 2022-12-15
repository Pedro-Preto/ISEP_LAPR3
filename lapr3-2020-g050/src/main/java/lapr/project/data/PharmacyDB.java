package lapr.project.data;

import lapr.project.model.DomainClasses.LandPath;
import lapr.project.model.DomainClasses.Pharmacy;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDB extends DataHandler {

    public Pharmacy getPharmacy(String nif) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, nif);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String pharmacyNif = rSet.getString(1);
                String pharmacyDesignation = rSet.getString(2);
                int addressId = rSet.getInt(3);

                return new Pharmacy(pharmacyNif, pharmacyDesignation, addressId);//trocar farmacia para este modelo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No Pharmacy with ID:" + nif);
    }

    public boolean addPharmacy(Pharmacy pharmacy) {
        return addPharmacy(pharmacy.getNif(), pharmacy.getDesignation(), pharmacy.getAddressId());
    }

    private boolean addPharmacy(String nif, String designation, int adress) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?) }");

            callStmt.setString(1, nif);
            callStmt.setString(2, designation);
            callStmt.setInt(3, adress);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removePharmacy(String nif) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");

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

    public List<Pharmacy> getAllPharmacies() {
        List<Pharmacy> pharmacies = new ArrayList<>();


        try (CallableStatement callStmt = getConnection().prepareCall("{?= call getAllPharmacies()}");) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                pharmacies.add(new Pharmacy(rSet.getString(1), rSet.getString(2), rSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return pharmacies;
    }
}
