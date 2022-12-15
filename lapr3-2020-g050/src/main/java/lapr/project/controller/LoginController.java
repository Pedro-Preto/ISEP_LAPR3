
package lapr.project.controller;

import lapr.project.model.autorization.model.UserRole;

public class LoginController
{
    private final ApplicationEM appEM;
    
    public LoginController()
    {
        this.appEM = ApplicationEM.getInstance();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
        return this.appEM.doLogin(strId, strPwd);
    }
    
    public UserRole getUserRole()
    {
        if (this.appEM.getUserSession().isLoggedIn())
        {
            Integer roleId = this.appEM.getUserSession().getUserRole();
            return appEM.getPlatform().getAutorization().getUserRoleByID(roleId);
        }
        return null;
    }

    public void doLogout()
    {
        this.appEM.doLogout();
    }
}
