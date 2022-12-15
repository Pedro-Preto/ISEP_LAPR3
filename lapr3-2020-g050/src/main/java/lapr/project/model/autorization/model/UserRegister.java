
package lapr.project.model.autorization.model;

import lapr.project.data.UserDB;

public class UserRegister
{
    private final UserDB userDB = new UserDB();
    
    
    public User newUser(String name, String emailAddress, String password)
    {
        return new User(name, emailAddress, password);
    }
    
    public boolean addUser(User user)
    {
        if (user != null)
            return userDB.addUser(user);
        return false;
    }
    
    public User searchUser(String id)
    {
        try {
            return userDB.getUser(id);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean hasUser(String id)
    {
        try{
            userDB.getUser(id);
            return true;
        } catch (IllegalArgumentException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String emailAddress){
        return this.userDB.removeUser(emailAddress);
    }
}
