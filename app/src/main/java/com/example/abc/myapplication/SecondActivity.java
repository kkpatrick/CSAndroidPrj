package com.example.abc.myapplication;

import android.graphics.Color;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;

public class SecondActivity extends Activity {
    public static final String EXTRA_WHICH_DOOR="which door";
    public static final String BOOL_WHICH_DOOR_BUTTON_PRESSED="which door button pressed";
    private String buttonTextIn;
    private TextView textView;
    private Button showWhichDoor;
    private boolean isWhichDoorButtonPressed = false;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BOOL_WHICH_DOOR_BUTTON_PRESSED, isWhichDoorButtonPressed);
        outState.putString(EXTRA_WHICH_DOOR, buttonTextIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.TextView1);
        showWhichDoor = (Button) findViewById(R.id.button1);
        showWhichDoor.setOnClickListener(clickButton);

        if(null == savedInstanceState) {
            buttonTextIn = getIntent().getStringExtra(EXTRA_WHICH_DOOR);
            textView.setText(buttonTextIn);
            showWhichDoor.setText("Which door");
        }
        else{
            isWhichDoorButtonPressed =
                    savedInstanceState.getBoolean(BOOL_WHICH_DOOR_BUTTON_PRESSED);
            buttonTextIn = savedInstanceState.getString(EXTRA_WHICH_DOOR);
            if(isWhichDoorButtonPressed){
                showWhichDoor.setText(buttonTextIn);
                showWhichDoor.setBackgroundColor(Color.GREEN);
            }
            else{
                showWhichDoor.setText("Which door");
            }

        }

    }

    private OnClickListener clickButton = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            button.setText(buttonTextIn);
            button.setBackgroundColor(Color.GREEN);
            isWhichDoorButtonPressed = true;
        }
    };
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
