package com.example.abc.myapplication;

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
    }
    public void addOne() {
        this.number++;
        NumberChanged numberChangedObj = new NumberChanged(this.Id, this.number);
        EventBus.getDefault().post(numberChangedObj);
    }
}
