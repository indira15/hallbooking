package com.example.hallbook.hallbooking.fcm;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */


import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HallBookingApplication extends Application {

    private Retrofit mRetrofit;

    public static HallBookingApplication getInstance() {
        return sHallBookingApplication;
    }

    private static HallBookingApplication sHallBookingApplication;
    private final String API_URL = "https://manishkumarjangid7.000webhostapp.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        sHallBookingApplication = this;
    }


    public Retrofit getRetrofit() {

            if (mRetrofit == null) {
                try {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();



                } catch (Exception e) {
                    Log.e("log_tag", "Error:  " + e.toString());
                }
            }return mRetrofit;
    }
}


