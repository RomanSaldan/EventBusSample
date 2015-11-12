package com.lynx.ebsample2.events;

/**
 * Created by WORK on 20.10.2015.
 */
public class BatteryEvent {

    private boolean isCharging;

    public BatteryEvent(boolean isCharging) {
        this.isCharging = isCharging;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setIsCharging(boolean isCharging) {
        this.isCharging = isCharging;
    }
}
