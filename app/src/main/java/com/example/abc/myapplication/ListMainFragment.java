package com.example.abc.myapplication;

import android.app.Fragment;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import de.greenrobot.event.EventBus;

/**
 * Created by abc on 3/4/15.
 */
public class ListMainFragment extends Fragment {
    private ListView listView;
    private Button addButton;
    private myCursorAdapter adapter;
    private Cursor listCursor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        listView = (ListView)rootView.findViewById(R.id.listView1);
        listCursor = Database.getAllData();
        adapter = new myCursorAdapter(getActivity(), R.layout.list_item, listCursor, false);
        listView.setAdapter(adapter);

        addButton = (Button)rootView.findViewById(R.id.button1);
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
        return rootView;
    }

    public void onEventMainThread(Cursor c) {
        adapter.swapCursor(c);
        listCursor = c;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
