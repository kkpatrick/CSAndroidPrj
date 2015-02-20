package com.example.abc.myapplication;

//import android.support.v7.app.ActionBarActivity;
//import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

import static com.example.abc.myapplication.R.string.progress_dialog_sub_message;

public class NumberIncAsyncActivity extends Activity {
    private Number number1, number2;
    private TextView textView1, textView2;
    private Button button1, button2;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_inc_async);

        textView1 = (TextView)findViewById(R.id.number1);
        textView2 = (TextView)findViewById(R.id.number2);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        //init the var number
        number1 = new Number(R.id.number1);
        number2 = new Number(R.id.number2);
        button1.setOnClickListener(clickButton1);
        button2.setOnClickListener(clickButton2);
        textView1.setText("" + number1.getNumber());
        textView2.setText("" + number2.getNumber());
        //register the event bus
        EventBus.getDefault().register(this);
    }

    private View.OnClickListener clickButton1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number1.addOne();
        }
    };
    private View.OnClickListener clickButton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number2.addOne();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Number.NumberChanged numberChanged) {
        ((TextView)findViewById(numberChanged.getId())).setText(""+numberChanged.getValue());
    }

    public void onEventMainThread(Number.StartUpdate Object) {
        progressDialog = ProgressDialog.show(NumberIncAsyncActivity.this, getString(R.string.progress_dialog_top_message),
                getString(R.string.progress_dialog_sub_message));
    }

    public void onEventMainThread(Number.FinishUpdate Object) {
        progressDialog.dismiss();
    }

}
