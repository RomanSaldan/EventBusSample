package com.lynx.ebsample2.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lynx.ebsample2.MyBroadCastReceiver;
import com.lynx.ebsample2.MyService;
import com.lynx.ebsample2.R;
import com.lynx.ebsample2.events.AsyncEvent;
import com.lynx.ebsample2.events.BackgroundEvent;
import com.lynx.ebsample2.events.BatteryEvent;
import com.lynx.ebsample2.events.TimeEvent;
import com.lynx.ebsample2.events.ToastEvent;
import com.lynx.ebsample2.global.Variables;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private MyBroadCastReceiver mBR;
    private IntentFilter mFilter;

    @Bind(R.id.tvClock_AM)
    protected TextView tvClock_AM;

    @Bind(R.id.tvBattery_AM)
    protected TextView tvBattery_AM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initReceiver();

        //Get current battery status
        if(getBatteryStatus() == BatteryManager.BATTERY_STATUS_CHARGING) tvBattery_AM.setText("Charging...");
        else tvBattery_AM.setText("No charging");

        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mBR);
        stopService(new Intent(this, MyService.class));
    }


    //region EventBus handle methods
    public void onEventMainThread(TimeEvent event) {    // UI
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        tvClock_AM.setText(sdf.format(event.getCurrentDate()).toString());
    }

    public void onEventMainThread(BatteryEvent event) {
        if(event.isCharging()) {
            tvBattery_AM.setText("Charging...");
        } else {
            tvBattery_AM.setText("No charging");
        }
    }

    public void onEventBackgroundThread(BackgroundEvent backgroundEvent) {
        hardWork(6);
        EventBus.getDefault().post(new ToastEvent(backgroundEvent.getNum()));
    }

    public void onEventAsync(AsyncEvent asyncEvent) {
        hardWork(6);
        EventBus.getDefault().post(new ToastEvent(asyncEvent.getNum()));
    }

    public void onEventMainThread(ToastEvent toastEvent) {
        Snackbar.make(findViewById(R.id.rlRoot_AM), "Task " + toastEvent.getNum() + " finished!", Snackbar.LENGTH_SHORT).show();
        Variables.decrementCount();
    }
    //endregion

    private void initReceiver() {
        mBR = new MyBroadCastReceiver();

        mFilter = new IntentFilter();
        mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        mFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        mFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(mBR, mFilter);
    }

    private int getBatteryStatus() {
        Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status;
    }

    private void hardWork(int _sec) {
        try {
            TimeUnit.SECONDS.sleep(_sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
