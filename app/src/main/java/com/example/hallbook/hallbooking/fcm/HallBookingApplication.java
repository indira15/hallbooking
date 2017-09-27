package com.example.hallbook.hallbooking.fcm;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */


import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HallBookingApplication extends Application {

    private Retrofit mRetrofit;

    public static HallBookingApplication getInstance() {
        return sHallBookingApplication;
    }

    private static HallBookingApplication sHallBookingApplication;
    private final String API_URL = "https://blxusedbooks.000webhostapp.com/";
    @Override
    public void onCreate() {
        super.onCreate();
        sHallBookingApplication = this;
    }


    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
