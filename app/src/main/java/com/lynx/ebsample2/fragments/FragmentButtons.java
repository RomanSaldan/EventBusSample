package com.lynx.ebsample2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lynx.ebsample2.R;
import com.lynx.ebsample2.events.AsyncEvent;
import com.lynx.ebsample2.events.BackgroundEvent;
import com.lynx.ebsample2.events.ImageEvent;
import com.lynx.ebsample2.global.Constants;
import com.lynx.ebsample2.global.Variables;

import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by WORK on 20.10.2015.
 */
public class FragmentButtons extends Fragment {

    @OnClick(R.id.btnLoadNow_FB)
    protected void clickLoadNow() {
        EventBus.getDefault().post(new ImageEvent(Constants.IMAGE_URL));
    }

    @OnClick(R.id.btnTaskBackground_FB)
    protected void clickTaskBackground() {
        EventBus.getDefault().post(new BackgroundEvent(Variables.incrementCount()));
    }

    @OnClick(R.id.btnTaskAsync_FB)
    protected void clickTaskAsync() {
        EventBus.getDefault().post(new AsyncEvent(Variables.incrementCount()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
