package com.example.abc.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends Activity {
    private Button Button1, Button2, Button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button1 = (Button)findViewById(R.id.button1);
        Button2 = (Button)findViewById(R.id.button2);
        Button3 = (Button)findViewById(R.id.button3);

        Button1.setOnClickListener(clickButton);
        Button2.setOnClickListener(clickButton);
        Button3.setOnClickListener(clickButton);
    }

    private OnClickListener clickButton = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            String buttonText = (String)button.getText();
            Intent intent = new Intent(v.getContext(), SecondActivity.class);
            intent.putExtra(SecondActivity.EXTRA_WHICH_DOOR, buttonText);
            startActivity(intent);
        };
    };


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/
/*
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
