package com.psi.gardenerasistance;

import java.util.UUID;

public class MachineryViewModel
{
    UUID id;
    String name;

    public MachineryViewModel(String name)
    {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
