package com.example.hallbook.hallbooking.fcm;

import com.example.hallbook.hallbooking.entity.Response;
import com.example.hallbook.hallbooking.entity.Owners;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by ARUN SUTHAR on 27-09-2017.
 */

public interface GetOwnerInterface {




    @POST("loginowner.php")
    @FormUrlEncoded
    Call<Response> loginOwner(@Field("email") String email,
                             @Field("password") String password);

    @POST("test.php")
    @Multipart
    Call<Response> addOwner(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part imageurl);

    @GET("getallhall.php")
    Call<Owners> getOwners();





}







