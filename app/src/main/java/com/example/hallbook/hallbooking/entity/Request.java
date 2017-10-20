package com.example.hallbook.hallbooking.entity;

/**
 * Created by ARUN SUTHAR on 17-10-2017.
 */

public class Request {


    private String operation;
    private User user;
    private Owner owner;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
