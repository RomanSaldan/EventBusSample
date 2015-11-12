package com.lynx.ebsample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.lynx.ebsample2.events.BatteryEvent;
import com.lynx.ebsample2.events.DelayEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by WORK on 20.10.2015.
 */
public class MyBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if(netInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    EventBus.getDefault().post(new DelayEvent(1));
                } else if(netInfo.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                    EventBus.getDefault().post(new DelayEvent(2));
                }
            case Intent.ACTION_POWER_CONNECTED:
                EventBus.getDefault().post(new BatteryEvent(true));
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                EventBus.getDefault().post(new BatteryEvent(false));
                break;
        }
    }

}
