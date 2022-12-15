
package lapr.project.model.autorization.model;

import lapr.project.data.UserRoleDB;

public class UserRoleRegister
{

    private final UserRoleDB userRoleDB = new UserRoleDB();
    
    public UserRole newUserRole(String role)
    {
        return new UserRole(role);
    }

    public UserRole newUserRole(int id, String role, String description)
    {
        return new UserRole(id, role, description);
    }
    
    public boolean addRole(UserRole role)
    {
        if (role != null)
            return userRoleDB.addUserRole(role);
        return false;
    }
    
    public UserRole searchRole(String role)
    {
        return userRoleDB.getUserRole(role);
    }

    public UserRole searchRoleById(Integer roleId)
    {
        return userRoleDB.getUserRoleByID(roleId);
    }

    public boolean deleteUserRole(Integer roleID) {
        return this.userRoleDB.removeUserRole(roleID);
    }

    public boolean deleteUserRole(String role) {
        return this.userRoleDB.removeUserRole(role);
    }

    public boolean deleteUserRoleAndUsers(Integer roleID) {
        return this.userRoleDB.removeUserRoleAndUsers(roleID);
    }
}
