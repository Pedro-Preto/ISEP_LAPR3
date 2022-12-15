
package lapr.project.model.autorization.model;

import java.util.Objects;


public class User
{
    private final String name;
    private final String emailAddress;
    private final String password;
    private int userRoleId;
    
    public User(String name, String email, String password)
    {
    
        if ( (name == null) || (email == null) || (password == null) || (name.isEmpty()) || (email.isEmpty()) || (password.isEmpty()))
            throw new IllegalArgumentException("Nenhum dos argumentos não pode ser nulo ou vazio.");
        
        this.name = name;
        this.emailAddress = email;
        this.password = password;
        
    }

    public User(String email, String pwd, int userRoleId){
        this.name = "";
        this.emailAddress=email;
        this.password = pwd;
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    
    public boolean hasID(String id)
    {
        return this.emailAddress.equals(id);
    }
    
    public boolean hasPassword(String password)
    {
        return this.password.equals(password);
    }
    
    public boolean addRole(UserRole role)
    {
        if (role != null){
            this.userRoleId = role.getId();
            return true;
        }
        return false;
    }
    
    public int getUserRoleId()
    {
        return this.userRoleId;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress) && Objects.equals(password, user.password) && Objects.equals(userRoleId, user.userRoleId);
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.name, this.emailAddress);
    }

}
