package com.example.abc.myapplication;

import android.content.Intent;
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
    public static final String CHOOSE_A_DOOR="Choose a door.";
    public static final String DOOR_ID="door Id";
    public static final String TEXT_VIEW_CONTENT="Text view content";
    public static final String DOOR_NUMBER="door number";
    private String buttonIdText;
    private TextView textView;
    private Button doorButton1, doorButton2, doorButton3, resetButton;
    private boolean isWhichDoorButtonPressed = false;
    private int pressedButtonId;
    private int doorNumber;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BOOL_WHICH_DOOR_BUTTON_PRESSED, isWhichDoorButtonPressed);
        outState.putString(EXTRA_WHICH_DOOR, buttonIdText);
        outState.putInt(DOOR_ID, pressedButtonId);
        outState.putString(TEXT_VIEW_CONTENT, textView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.TextView1);
        doorButton1 = (Button) findViewById(R.id.button1);
        doorButton2 = (Button) findViewById(R.id.button2);
        doorButton3 = (Button) findViewById(R.id.button3);
        resetButton = (Button) findViewById(R.id.button4);

        doorButton1.setOnClickListener(clickButton);
        doorButton2.setOnClickListener(clickButton);
        doorButton3.setOnClickListener(clickButton);
        resetButton.setOnClickListener(clickResetButton);

        if(null == savedInstanceState) {
            buttonIdText = getIntent().getStringExtra(EXTRA_WHICH_DOOR);
            textView.setText(CHOOSE_A_DOOR);
            doorNumber = getIntent().getIntExtra(DOOR_NUMBER, 0);
        }
        else{
            isWhichDoorButtonPressed =
                    savedInstanceState.getBoolean(BOOL_WHICH_DOOR_BUTTON_PRESSED);
            buttonIdText = getIntent().getStringExtra(EXTRA_WHICH_DOOR);
            pressedButtonId = savedInstanceState.getInt(DOOR_ID);
            textView.setText(savedInstanceState.getString(TEXT_VIEW_CONTENT));
            if(isWhichDoorButtonPressed){
                switch (pressedButtonId){
                    case R.id.button1:
                        doorButton1.setBackgroundColor(Color.GREEN);
                    break;
                    case R.id.button2:
                        doorButton1.setBackgroundColor(Color.GREEN);
                    break;
                    case R.id.button3:
                        doorButton1.setBackgroundColor(Color.GREEN);
                    break;
                    default:
                        break;
                }
            }
        }

    }

    private OnClickListener clickButton = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            //button.setBackgroundColor(Color.GRAY);
            isWhichDoorButtonPressed = true;
            textView.setText("The correct is " + buttonIdText);
            switch (v.getId()){
                case R.id.button1:
                    if(doorNumber == 1){
                        button.setBackgroundColor(Color.GREEN);
                    }
                break;
                case R.id.button2:
                    if(doorNumber == 2){
                        button.setBackgroundColor(Color.GREEN);
                    }
                break;
                case R.id.button3:
                    if(doorNumber == 3){
                        button.setBackgroundColor(Color.GREEN);
                    }
                break;
                default:
                    break;
            }
            doorButton1.setEnabled(false);
            doorButton2.setEnabled(false);
            doorButton3.setEnabled(false);
        }
    };
    private OnClickListener clickResetButton = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
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
