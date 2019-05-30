package com.psi.gardenerasistance;

import java.util.Date;
import java.util.UUID;

public class MessageViewModel
{
    int id;
    String title;
    String message;
    //UUID sender;
    //UUID receiver;
    //For now use strings, later will use references
    String sender;
    String receiver;
    Date sentDate;
    Date receivedDate;
    TypesEnum type;

    public MessageViewModel(String title, String message, String sender, String receiver, /*UUID sender, UUID receiver,*/ Date sentDate, Date receivedDate, TypesEnum type)
    {
        this.title = title;
        this.message = message;
        this.type = type;
        this.sentDate = sentDate;
        this.receivedDate = receivedDate;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getTitle(){
        return title;
    }

    public String getMessage(){
        return message;
    }

    public String getSender(){
        return sender;
    }

    public String getReceiver(){
        return receiver;
    }

    public Date getSentDate(){
        return sentDate;
    }

    public Date getReceivedDate(){
        return receivedDate;
    }

    public TypesEnum getType(){
        return type;
    }

    enum TypesEnum
    {
        System,
        User
    }
}
