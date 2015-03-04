package com.example.abc.myapplication;

import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abc.myInterface.HasGetDetailNumber;

import de.greenrobot.event.EventBus;


public class ListViewActivity extends ActionBarActivity implements HasGetDetailNumber{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_list_view);
            if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
                getFragmentManager().beginTransaction()
                        .add(android.R.id.content,
                                new ListMainFragment()).commit();
            }
        }
        else {
            setContentView(R.layout.activity_list_view_land);
        }
    }

    @Override
    public int getDetailNumber() {
        //int number = Database.getNumberWithId(getIntent().getIntExtra(NUMBER_ID, 0)).getNumber();
        return 0;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
