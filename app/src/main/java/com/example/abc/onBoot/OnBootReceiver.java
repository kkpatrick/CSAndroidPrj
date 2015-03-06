package com.example.abc.onBoot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by abc on 3/6/15.
 */
public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getSimpleName(), "Hi, Mom!");
    }
}
