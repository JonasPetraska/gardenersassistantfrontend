package com.psi.gardenerasistance;

import java.util.UUID;

public class WorkerViewModel
{
    UUID id;
    String firstName;
    String lastName;

    public WorkerViewModel(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
