package com.lynx.ebsample2.events;

/**
 * Created by WORK on 02.11.2015.
 */
public abstract class BaseWorkEvent {

    private int num;

    public BaseWorkEvent(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
