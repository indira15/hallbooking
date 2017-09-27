package com.example.hallbook.hallbooking.entity;

/**
 * Created by ARUN SUTHAR on 27-09-2017.
 */

import java.io.Serializable;


public class Owner  implements Serializable{


    private int id;
    private String ownername;
    private String owneremail;
    private String  owneraddress;
    private String ownerphoneno;
    private String ownerphoneno2;
    private String ownercity;
    private String ownerstate;
    private String ownerpassword;
    private String ownerparking;
    private String ownerseatcapacity;
    private String ownerdinningcapacity;



    public void setOwnername(String name) {this.ownername = name;}

    public void setEmail(String email) {
        this.owneremail = email;
    }

    public void setAddress(String address) {
        this.owneraddress = address;
    }

    public void setPhoneno(String phoneno) { this.ownerphoneno = phoneno; }

    public void setPhoneno2(String phoneno2) { this.ownerphoneno2 = phoneno2;}

    public void setCity(String city) { this.ownercity=city;}

    public void setState(String state) {this.ownerstate=state;}

    public void setPassword(String password){this.ownerpassword=password;}

    public String getOwnername() { return ownername;}

    public String getEmail() {
        return owneremail;
    }

    public String getAddress() {
        return owneraddress;
    }

    public String getPhoneno() {
        return ownerphoneno;
    }

    public String getPhoneno2() {
        return ownerphoneno2;
    }

    public  String getCity() { return ownercity;}

    public  String getState() {return ownerstate;}

    public String getPassword() {return ownerpassword;}

    public int getId() {
        return id;
    }

}
