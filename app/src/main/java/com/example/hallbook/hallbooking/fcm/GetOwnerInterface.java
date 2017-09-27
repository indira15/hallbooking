package com.example.hallbook.hallbooking.fcm;

import com.example.hallbook.hallbooking.entity.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ARUN SUTHAR on 27-09-2017.
 */

public interface GetOwnerInterface {




    @POST("login.php")
    @FormUrlEncoded
    Call<Response> loginUser(@Field("email") String email,
                             @Field("password") String password);


    @POST("register.php")
    @FormUrlEncoded
    Call<Response> addOwner(
            @Field("ownername") String ownername,
            @Field("ownernameemail") String owneremail,
            @Field("ownernamepassword") String ownerpassword,
            @Field("ownernamephoneno") String ownerphoneno,
            @Field("ownernamephoneno2") String ownerphoneno2,
            @Field("ownernameaddress") String owneraddress,
            @Field("city") String city,
            @Field("state") String state);


}







