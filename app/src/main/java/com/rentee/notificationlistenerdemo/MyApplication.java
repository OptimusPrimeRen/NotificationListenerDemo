package com.rentee.notificationlistenerdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by rentee on 17-1-12.
 */

public class MyApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
