package com.example.hallbook.hallbooking.fcm;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import static com.example.hallbook.hallbooking.R.string.message;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */

public class Utils  {

    public static boolean isActivityAlive(Activity activity)
    {
     return !(null == activity || activity.isFinishing() || activity.isDestroyed());

    }
    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
