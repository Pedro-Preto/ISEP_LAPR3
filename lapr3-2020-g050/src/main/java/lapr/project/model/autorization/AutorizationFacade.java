
package lapr.project.model.autorization;

import lapr.project.model.autorization.model.*;

public class AutorizationFacade
{
    private UserSession userSession = null;
    
    private final UserRoleRegister roleRegister = new UserRoleRegister();
    private final UserRegister userRegister = new UserRegister();
    
    public boolean registerUserRole(Integer id, String role)
    {
        UserRole userRole = this.roleRegister.newUserRole(id, role, role);
        return this.roleRegister.addRole(userRole);
    }

    public boolean deleteUserRole(Integer roleID){
        return this.roleRegister.deleteUserRole(roleID);
    }

    public boolean deleteUserRole(String role){
        return this.roleRegister.deleteUserRole(role);
    }

    public boolean deleteUserRoleAndUsers(Integer roleID){
        return this.roleRegister.deleteUserRoleAndUsers(roleID);
    }
    


    public boolean registerUserRole(int id, String role, String description)
    {
        UserRole userRole = this.roleRegister.newUserRole(id, role, description);
        return this.roleRegister.addRole(userRole);
    }


    
    public boolean registerUserWithRole(String name, String emailAddress, String password, String role)
    {
        UserRole userRole = this.roleRegister.searchRole(role);
        User user = this.userRegister.newUser(name, emailAddress, password);
        user.addRole(userRole);
        return this.userRegister.addUser(user);
    }
    public boolean deleteUser(String emailAddress){
        return this.userRegister.deleteUser(emailAddress);
    }

    public boolean hasRegisteredUser(String id)
    {
        return this.userRegister.hasUser(id);
    }
    
    
    public UserSession doLogin(String id, String password)
    {
        User user = this.userRegister.searchUser(id);
        if (user != null)
        {
            if (user.hasPassword(password)){
                this.userSession = new UserSession(user);
            }
        }
        return getUserSession();
    }
    
    public UserSession getUserSession()
    {
        return this.userSession;
    }
    
    public void doLogout()
    {
        if (this.userSession != null)
            this.userSession.doLogout();
        this.userSession = null;
    }

    public UserRole getUserRoleByID(Integer userRoleID){
        if(userRoleID != null)
            return this.roleRegister.searchRoleById(userRoleID);
        return null;
    }
}
