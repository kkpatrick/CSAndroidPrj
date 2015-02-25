package com.example.abc.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by abc on 2/19/15.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    public static final String My_APPLICATION_NUMBER_SAVED="my_application_number_saved";
    private static SharedPreferences sharedPref;
    //private static Database database;

    public static MyApplication getInstance() {
        return instance;
    }

    public static SharedPreferences getSharedPref() {
        return sharedPref;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.d("MyApplication", "MyApplication initiated");
        Context context = getInstance();
        this.sharedPref = context.getSharedPreferences(
                My_APPLICATION_NUMBER_SAVED, Context.MODE_PRIVATE);

    }


}
