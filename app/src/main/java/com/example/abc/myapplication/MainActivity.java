package com.example.abc.myapplication;

//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.view.Menu;
import android.app.Activity;
//import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {
    private Button Button1, Button2, Button3;

    private static boolean isExit = false;

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

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
            int doorNumber;
            switch(v.getId()){
                case R.id.button1:
                    doorNumber = 1;
                    break;
                case R.id.button2:
                    doorNumber = 2;
                    break;
                case R.id.button3:
                    doorNumber = 3;
                    break;
                default:
                    doorNumber = 0;
                    break;
            }
            Button button = (Button)v;
            String buttonText = (String)button.getText();
            Intent intent = new Intent(v.getContext(), SecondActivity.class);
            //Intent intent = new Intent(v.getContext(), ForTestActivity.class);
            intent.putExtra(SecondActivity.EXTRA_WHICH_DOOR, buttonText);
            intent.putExtra(SecondActivity.DOOR_NUMBER, doorNumber);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "Press back again to exit",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
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
