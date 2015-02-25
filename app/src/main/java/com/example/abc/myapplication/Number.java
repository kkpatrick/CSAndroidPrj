package com.example.abc.myapplication;

import android.database.Cursor;
import android.os.AsyncTask;
//import android.provider.ContactsContract;

import de.greenrobot.event.EventBus;

/**
 * Created by abc on 2/17/15.
 */
public class Number {
    private int number;
    private int Id;

    public static class NumberChanged {
        private int value;
        private int Id;
        public NumberChanged(int Id, int value) {
            this.Id = Id;
            this.value = value;
        }

        public int getId() {
            return Id;
        }

        public int getValue() {
            return value;
        }
    }

    public Number(int Id) {
        this.Id = Id;
        //this.number = MyApplication.getSharedPref().getInt("" + MyApplication.My_APPLICATION_NUMBER_SAVED + Id, 0);
        if(null == Database.getNumberWithId(Id)) {
            this.number = 0;
        }
        else {
            this.number = Database.getNumberWithId(Id).number;
        }
    }

    public Number(int Id, int number) {
        this.Id = Id;
        this.number = number;
    }

    public void addOne() {
        if(null == Database.getNumberWithId(Id)) {
            this.number = 0;
        }
        else {
            this.number = Database.getNumberWithId(Id).number;
        }
        this.number++;
        EventBus.getDefault().post(new StartUpdate());//indicate the UI activity to start update
        new updateNumberAsyncTask().execute(new NumberChanged(Id, number));
    }

    protected class updateNumberAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //loop for delay simulation
            /*
            for (int i=0;i<= 400000000;i++)
            {
                int j=i*i;
            }*/
            //MyApplication.getSharedPref().edit().putInt("" + MyApplication.My_APPLICATION_NUMBER_SAVED + Id, number).commit();
            Database.updateNumber(Number.this);
            Cursor cursor = Database.getAllData();
            EventBus.getDefault().post(cursor);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            EventBus.getDefault().post(new FinishUpdate());
        }
    }

    public static class StartUpdate{}
    public static class FinishUpdate{}

    public int getNumber() {
        return this.number;
    }

    public int getId() {
        return this.Id;
    }
}
