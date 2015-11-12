package com.lynx.ebsample2;

import android.app.IntentService;
import android.content.Intent;

import com.lynx.ebsample2.events.DelayEvent;
import com.lynx.ebsample2.events.TimeEvent;
import com.lynx.ebsample2.global.Constants;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;


/**
 * Created by WORK on 20.10.2015.
 */
public class MyService extends IntentService {

    private long DELAY = Constants.DEFAULT_DELAY;
    private boolean isStarted;

    public MyService() {
        super("TimerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (isStarted) {
            EventBus.getDefault().post(new TimeEvent(new Date()));
            try {
                TimeUnit.SECONDS.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isStarted = true;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStarted = false;
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(DelayEvent event) {
        DELAY = event.getDelay();
    }
}
