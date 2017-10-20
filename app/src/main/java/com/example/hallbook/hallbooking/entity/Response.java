package com.example.hallbook.hallbooking.entity;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */

public class Response {

    private int success;
    private String message;
    private User user;
    private Owner owner;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public Owner getOwner()
    {
        return owner;
    }
}
