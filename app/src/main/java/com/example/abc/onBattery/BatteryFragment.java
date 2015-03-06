package com.example.abc.onBattery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abc.myapplication.R;


public class BatteryFragment extends Fragment {
    private ProgressBar bar=null;
    private ImageView status=null;
    private TextView level=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.batt, container, false);

        bar = (ProgressBar)result.findViewById(R.id.bar);
        status = (ImageView)result.findViewById(R.id.status);
        level = (TextView)result.findViewById(R.id.level);

        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter f = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(onBattery, f);
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(onBattery);
        super.onPause();
    }

    BroadcastReceiver onBattery=new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int pct=
                    100 * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 1)
                            / intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);

            bar.setProgress(pct);
            level.setText(String.valueOf(pct));

            switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    status.setImageResource(R.drawable.charging);
                    break;

                case BatteryManager.BATTERY_STATUS_FULL:
                    int plugged=
                            intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

                    if (plugged == BatteryManager.BATTERY_PLUGGED_AC
                            || plugged == BatteryManager.BATTERY_PLUGGED_USB) {
                        status.setImageResource(R.drawable.full);
                    }
                    else {
                        status.setImageResource(R.drawable.unplugged);
                    }
                    break;

                default:
                    status.setImageResource(R.drawable.unplugged);
                    break;
            }
        }
    };
}
