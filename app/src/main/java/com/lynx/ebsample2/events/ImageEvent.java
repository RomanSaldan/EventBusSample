package com.lynx.ebsample2.events;


import java.util.concurrent.ExecutionException;

/**
 * Created by WORK on 20.10.2015.
 */
public class ImageEvent {

    private String url;

    public ImageEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
