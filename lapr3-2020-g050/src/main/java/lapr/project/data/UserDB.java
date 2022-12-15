package lapr.project.data;

import lapr.project.model.autorization.model.User;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB extends DataHandler {

    public User getUser(String email) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUser(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String userEmail = rSet.getString(1);
                String userPwd = rSet.getString(2);
                int userRoleId = rSet.getInt(3);

                return new User(userEmail,userPwd,userRoleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        throw new IllegalArgumentException("No User with email:" + email);
    }

    public boolean addUser(User user) {
        return addUser(user.getEmailAddress(), user.getPassword(), user.getUserRoleId());
    }

    private boolean addUser(String uemail, String upwd, int uroleid) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call addUser(?,?,?) }");

            callStmt.setString(1, uemail);
            callStmt.setString(2, upwd);
            callStmt.setInt(3, uroleid);

            callStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll();
        }
    }

    public boolean removeUser(String emailAddress) {
        try {

            CallableStatement callStmt = getConnection().prepareCall("{ call removeUser(?) }");

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
}
