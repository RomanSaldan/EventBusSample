package com.lynx.ebsample2.events;

import java.util.Date;

/**
 * Created by WORK on 20.10.2015.
 */
public class TimeEvent {
    private Date mCurrentDate;

    public TimeEvent(Date mCurrentDate) {
        this.mCurrentDate = mCurrentDate;
    }

    public Date getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(Date mCurrentDate) {
        this.mCurrentDate = mCurrentDate;
    }
}
