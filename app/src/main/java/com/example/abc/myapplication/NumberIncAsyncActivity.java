package com.example.abc.myapplication;

//import android.support.v7.app.ActionBarActivity;
//import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class NumberIncAsyncActivity extends Activity {
    private Number number;
    private TextView textView1, textView2;
    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_inc_async);

        textView1 = (TextView)findViewById(R.id.number1);
        textView2 = (TextView)findViewById(R.id.number2);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        //init the var number
        number = new Number(0);
        button1.setOnClickListener(clickButton1);

        //register the event bus
        EventBus.getDefault().register(this);
    }

    private View.OnClickListener clickButton1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number.addOne();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Number.NumberChanged numberChanged) {
        textView1.setText(""+numberChanged.getValue());
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_inc_async, menu);
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
