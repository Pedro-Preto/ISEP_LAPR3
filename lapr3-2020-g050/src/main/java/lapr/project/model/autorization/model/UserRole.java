
package lapr.project.model.autorization.model;

import java.util.Objects;

public class UserRole
{
    private int id;
    private String role;
    private final String description;
    
    public UserRole(String role)
    {
        if ( (role == null) || (role.isEmpty()))
            throw new IllegalArgumentException("The parameters can't be null or empty!");
        
        this.role = role;
        this.description = role;
    }

    public UserRole(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public UserRole(int id, String role, String description)
    {
        if ( id <= 0 || (role == null) || (description == null) || (role.isEmpty())|| (description.isEmpty()))
            throw new IllegalArgumentException("The parameters can't be null or empty!");

        this.id = id;
        this.role = role;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasId(String strId)
    {
        return this.role.equals(strId);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(role, userRole.role) && Objects.equals(description, userRole.description);
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.role, this.description);
    }
}
