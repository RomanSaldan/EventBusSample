package com.lynx.ebsample2.events;

/**
 * Created by WORK on 20.10.2015.
 */
public class DelayEvent {

    private long delay;

    public DelayEvent(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
