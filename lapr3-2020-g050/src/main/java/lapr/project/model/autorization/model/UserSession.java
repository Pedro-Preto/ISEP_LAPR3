
package lapr.project.model.autorization.model;

public class UserSession
{
    private User user;
    
    public UserSession(User oUser)
    {
        if (oUser == null)
            throw new IllegalArgumentException("The parameters can't be null or empty!");
        this.user = oUser;
    }

    public void doLogout()
    {
        this.user = null;
    }

    public boolean isLoggedIn()
    {
        return this.user != null;
    }

    public User getUser(){
        if (isLoggedIn())
            return this.user;
        return null;
    }

    public String getUserEmail()
    {
        if (isLoggedIn())
            return this.user.getEmailAddress();
        return null;
    }
    
    public Integer getUserRole()
    {
        if (isLoggedIn())
            return this.user.getUserRoleId();
        return null;
    }
}
