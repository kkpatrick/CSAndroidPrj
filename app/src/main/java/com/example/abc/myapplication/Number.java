package com.example.abc.myapplication;

import android.os.AsyncTask;

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
        this.number = MyApplication.getSharedPref().getInt("" + MyApplication.My_APPLICATION_NUMBER_SAVED + Id, 0);
    }
    public void addOne() {
        this.number++;
        EventBus.getDefault().post(new StartUpdate());//indicate the UI activity to start update
        new updateNumberAsyncTask().execute(new NumberChanged(number, Id));
    }

    protected class updateNumberAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //loop for delay simulation
            for (int i=0;i<= 400000000;i++)
            {
                int j=i*i;
            }
            MyApplication.getSharedPref().edit().putInt("" + MyApplication.My_APPLICATION_NUMBER_SAVED + Id, number).commit();
            NumberChanged numberChangedObj = new NumberChanged(Id, number);
            EventBus.getDefault().post(numberChangedObj);
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
