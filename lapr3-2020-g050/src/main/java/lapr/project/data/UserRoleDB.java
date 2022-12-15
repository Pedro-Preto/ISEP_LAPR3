package lapr.project.data;

import lapr.project.model.autorization.model.User;
import lapr.project.model.autorization.model.UserRole;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDB extends DataHandler{

    public UserRole getUserRole(String designation) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUserRoleByRole(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, designation);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int userRoleId = rSet.getInt(1);
                String role = rSet.getString(2);
                String userRoleDesignation = rSet.getString(3);

                return new UserRole(userRoleId, role, userRoleDesignation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No UserRole with designation:" + designation);
    }

    public UserRole getUserRoleByID(Integer userRoleID) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUserRoleByID(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, userRoleID);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int userRoleId = rSet.getInt(1);
                String role = rSet.getString(2);
                String userRoleDesignation = rSet.getString(3);

                return new UserRole(userRoleId, role, userRoleDesignation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("No UserRole with id:" + userRoleID);
    }

    public boolean addUserRole(UserRole userRole) {
        return addUserRole(userRole.getId(), userRole.getRole(), userRole.getDescription());
    }

    private boolean addUserRole(int urid, String role, String urdesignation) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ call addUserRole(?,?,?) }");

            callStmt.setInt(1, urid);
            callStmt.setString(2, role);
            callStmt.setString(3, urdesignation);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeUserRole(int uid) {
        CallableStatement callStmt = null;
        try {

            callStmt = getConnection().prepareCall("{ call removeUserRole(?) }");

            callStmt.setInt(1, uid);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            closeAll();
        }
    }

    public boolean removeUserRole(String role) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeUserRole(?) }");

            callStmt.setString(1, role);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeUserRoleAndUsers(Integer roleID) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeUserRoleAndUsers(?) }");

            callStmt.setInt(1, roleID);

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
