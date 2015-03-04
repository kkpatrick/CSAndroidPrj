package com.example.abc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

/**
 * Created by abc on 2/25/15.
 */
public class myCursorAdapter extends ResourceCursorAdapter {

    public myCursorAdapter(Context context, int layout, Cursor c, boolean autoRequery) {
        super(context, layout, c, autoRequery);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.editText1);
        textView.setText("" + cursor.getInt(cursor.getColumnIndex(Database.KEY_VALUE)));
        final int numberId = cursor.getInt(cursor.getColumnIndex(Database.KEY_ID));

        //get tag and  set response for buttons
        final ViewHolder cache = (ViewHolder) view.getTag();
        cache.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //query database with the button id
                Number number = new Number(numberId);
                number.addOne();
            }
        });
        cache.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //query database with the button id
                Database.deleteNumber(numberId);
                EventBus.getDefault().post(Database.getAllData());
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Number number = new Number(numberId);
                Toast.makeText(view.getContext(), "list item clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.NUMBER_ID, number.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = super.newView(context, cursor, parent);
        ViewHolder holder = new ViewHolder();
        holder.textView = (TextView)view.findViewById(R.id.editText1);
        holder.addButton = (Button)view.findViewById(R.id.button1);
        holder.deleteButton = (Button)view.findViewById(R.id.delete1);
        view.setTag(holder);
        return view;
    }

    private class ViewHolder {
        private TextView textView;
        private Button addButton;
        private Button deleteButton;
    }
}
