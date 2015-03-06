package com.example.abc.onBoot;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abc.myapplication.R;

public class BootstrapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootstrap);
        Toast.makeText(this, R.string.activated, Toast.LENGTH_LONG).show();
        finish();
    }
}
