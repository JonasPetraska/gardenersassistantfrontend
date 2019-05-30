package Models;

import java.util.Date;
import java.util.List;

public class ApplicationUser
{
    public String Id;
    public List<Permission> Permissions;
    public UserTypeEnum UserType;
    public Date CreationDate;
    public String FirstName;
    public String LastName;
    public String UserName;
    public String Email;
    public String PhoneNumber;
    public String SecurityStamp;
    public String PasswordHash;

    enum UserTypeEnum
    {
        Worker,
        Owner,
        Administrator
    }

}
