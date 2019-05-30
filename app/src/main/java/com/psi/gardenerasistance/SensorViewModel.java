package com.psi.gardenerasistance;

import java.util.UUID;

public class SensorViewModel
{
    UUID id;
    String name;
    String displayParameter;
    TypesEnum type;

    public SensorViewModel(String name, String displayParameter, TypesEnum type)
    {
        this.name = name;
        this.displayParameter = displayParameter;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getDisplayParameter(){
        return displayParameter;
    }

    public TypesEnum getType(){
        return type;
    }

    enum TypesEnum
    {
        Temperature,
        Moisture
    }
}
