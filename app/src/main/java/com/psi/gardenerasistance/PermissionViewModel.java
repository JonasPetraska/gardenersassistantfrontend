package com.psi.gardenerasistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class PermissionViewModel implements Serializable
{
    UUID id;
    String firstName;
    String lastName;
    ArrayList<String> permissions;

    public PermissionViewModel(String firstName, String lastName, ArrayList<String> permissions)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getPermissions(){
        return permissions;
    }
}
