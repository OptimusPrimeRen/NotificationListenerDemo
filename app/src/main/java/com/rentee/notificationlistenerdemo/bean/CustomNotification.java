package com.rentee.notificationlistenerdemo.bean;

import android.app.PendingIntent;

/**
 * Created by rentee on 17-1-12.
 */

public class CustomNotification {
    /**
     * Notification的PendingIntent
     */
    private PendingIntent mPendingIntent;

    /**
     * Notification PenddingIntent中的Intent，然后序列化
     * 序列化主要原因是保存Intent，如果PendingIntent丢失，仍然可以通过保存的Intent跳转
     */
    private String mSerializeIntent;

    public PendingIntent getPendingIntent() {
        return mPendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        mPendingIntent = pendingIntent;
    }

    public String getSerializeIntent() {
        return mSerializeIntent;
    }

    public void setSerializeIntent(String serializeIntent) {
        mSerializeIntent = serializeIntent;
    }
}
