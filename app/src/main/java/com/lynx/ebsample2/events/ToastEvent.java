package com.lynx.ebsample2.events;

/**
 * Created by WORK on 22.10.2015.
 */
public class ToastEvent {
    private int num;

    public ToastEvent(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
