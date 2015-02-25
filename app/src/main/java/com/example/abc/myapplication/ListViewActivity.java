package com.example.abc.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import de.greenrobot.event.EventBus;


public class ListViewActivity extends Activity {
    private ListView listView;
    private Button addButton;
    private myCursorAdapter adapter;
    private Cursor listCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView)findViewById(R.id.listView1);
        listCursor = Database.getAllData();
        adapter = new myCursorAdapter(ListViewActivity.this, R.layout.list_item, listCursor, false);
        listView.setAdapter(adapter);

        addButton = (Button)findViewById(R.id.button1);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add a new item into the list
                Database.addNumber();
                listCursor = Database.getAllData();
                adapter.swapCursor(listCursor);
            }
        });

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Cursor c) {
        adapter.swapCursor(c);
        listCursor = c;
    }

}
