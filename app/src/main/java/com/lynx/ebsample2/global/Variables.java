package com.lynx.ebsample2.global;

/**
 * Created by WORK on 22.10.2015.
 */
public class Variables {

    private static int taskCont = 1;

    public static int incrementCount() {
        return taskCont++;
    }

    public static int decrementCount() {
        return taskCont--;
    }

}
