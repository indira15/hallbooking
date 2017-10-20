package com.example.hallbook.hallbooking.entity;

/**
 * Created by ARUN SUTHAR on 27-09-2017.
 */

import java.io.Serializable;


public class Owner  implements Serializable{


    private int id;
    private String hallname;
    private String ownername;
    private String owneremail;
    private String ownerpassword;
    private String owneraddress;
    private String ownerphoneno;
    private String ownerphoneno2;
    private String ownercity;
    private String ownerstate;
    private String parking;
    private String seatcapacity;
    private String dinningcapacity;
    private String ac;
    private String food;
    private String imageurl;



    public void setHallname(String hallname) {this.hallname = hallname;}

    public String getHallname() {return hallname;}

    public void setOwnername(String ownername) {this.ownername = ownername;}

    public String getOwnername() {return ownername;}

    public void setOwneremail(String owneremail) {this.owneremail = owneremail;}

    public String getOwneremail() {return owneremail;}

    public void setOwnerpassword(String ownerpassword) {this.ownerpassword = ownerpassword;}

    public String getOwnerpassword() {return ownerpassword;}

    public void setOwnerphoneno(String ownerphoneno) {this.ownerphoneno = ownerphoneno;}

    public String getOwnerphoneno() {return ownerphoneno;}

    public void setOwnerphoneno2(String ownerphoneno2) {this.ownerphoneno2 = ownerphoneno2;}

    public String getOwnerphoneno2() {return ownerphoneno2;}

    public void setOwneraddress(String owneraddress) {this.owneraddress = owneraddress;}

    public String getOwneraddress() {return owneraddress;}

    public void setOwnercity(String ownercity) {this.ownercity = ownercity;}

    public String getOwnercity() {return ownercity;}

    public void setOwnerstate(String ownerstate) {this.ownerstate = ownerstate;}

    public String getOwnerstate() {return ownerstate;}

    public void setseatcapacity(String seatcapacity) {this.seatcapacity = seatcapacity;}

    public String getSeatcapacity() {return seatcapacity;}

    public void setDinningcapacity(String dinningcapacity) {this.dinningcapacity = dinningcapacity;}

    public String getDinningcapacity() {return dinningcapacity;}

    public void setparking(String parking) {this.parking = parking;}

    public String getparking() {return parking;}


    public void setAc(String ac) {this.ac = ac;}

    public String getAc() {return ac;}

    public void setFood(String food) {this.food = food;}

    public String getFood() {return food;}

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public int getId() {
        return id;
    }


}
