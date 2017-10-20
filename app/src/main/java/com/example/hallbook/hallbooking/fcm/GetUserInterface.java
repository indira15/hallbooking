package com.example.hallbook.hallbooking.fcm;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */


import com.example.hallbook.hallbooking.entity.Response;
import retrofit2.Call;
//import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface GetUserInterface {

    @POST("login.php")
    @FormUrlEncoded
    Call<Response> loginUser(@Field("email") String email,
                             @Field("password") String password);


    @POST("register.php")
    @FormUrlEncoded
    Call<Response> addUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phoneno") String phoneno,
            @Field("phoneno2") String phoneno2,
            @Field("address") String address,
            @Field("city") String city,
            @Field("state") String state);




}
