package com.example.abc.myapplication;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.myInterface.HasGetDetailNumber;

import de.greenrobot.event.EventBus;

/**
 * Created by abc on 3/4/15.
 */
public class DetailFragment extends Fragment {
    private TextView text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        text = (TextView)rootView.findViewById(R.id.textView1);
        text.setText("" + ((HasGetDetailNumber) getActivity()).getDetailNumber());
        EventBus.getDefault().register(this);
        return rootView;
    }
    public void onEventMainThread(Integer numberId) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            /*
            DetailFragment detailFragment =
                    (DetailFragment) getFragmentManager().findFragmentById(R.id.DetailFragment);
            detailFragment.update(numberId);
            */
            update(numberId);
        }
    }
    public void update(Integer numberId) {
        text.setText(Database.getNumberWithId(numberId).getNumber() + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
