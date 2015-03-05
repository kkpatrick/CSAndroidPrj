package com.example.abc.hurl;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abc.myapplication.R;

import com.example.abc.hurl.dummy.DummyContent;

import java.util.List;

import de.greenrobot.event.EventBus;

public class QuestionsFragment extends ListFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*we avoid duplicating the LoadThread if a con<guration
        change occurs sometime after our fragment was initially created.
         */
        setRetainInstance(true);
        new LoadThread().start();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Item item=((ItemsAdapter)getListAdapter()).getItem(position);
        EventBus.getDefault().post(new QuestionClickedEvent(item));
    }

    public void onEventMainThread(QuestionsLoadedEvent event) {
        //we hold onto the loaded questions and populate the ListView.
        setListAdapter(new ItemsAdapter(event.questions.items));
    }

    class ItemsAdapter extends ArrayAdapter<Item> {
        ItemsAdapter(List<Item> items) {
        super(getActivity(), android.R.layout.simple_list_item_1, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row=super.getView(position, convertView, parent);
            TextView title=(TextView)row.findViewById(android.R.id.text1);
            title.setText(Html.fromHtml(getItem(position).title));
            return(row);
        }
    }

}
