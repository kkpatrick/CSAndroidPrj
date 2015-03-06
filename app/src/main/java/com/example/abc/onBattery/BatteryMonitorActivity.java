package com.example.abc.onBattery;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abc.myapplication.R;

public class BatteryMonitorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_monitor);

        if(getFragmentManager().findFragmentById(android.R.id.content) == null) {
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    new BatteryFragment()).commit();
        }
    }

}
