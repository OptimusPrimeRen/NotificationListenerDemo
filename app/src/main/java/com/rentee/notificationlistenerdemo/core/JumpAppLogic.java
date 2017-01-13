package com.rentee.notificationlistenerdemo.core;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.rentee.notificationlistenerdemo.MyApplication;
import com.rentee.notificationlistenerdemo.bean.CustomNotification;
import com.rentee.notificationlistenerdemo.util.LogUtil;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by rentee on 17-1-10.
 * 跳转逻辑
 */

public class JumpAppLogic {


    /**
     * 跳转app的逻辑，
     * 有PendingIntent优先PendingIntent跳转，
     * 如果PendingIntent失败，则再根据保存的Intent跳转
     *
     * @param customNotification
     */
    public static void jumpAppByNotification(CustomNotification customNotification) {
        LogUtil.d("jumpAppByNotification");
        if (jumpAppByPendingIntent(customNotification.getPendingIntent())) {
            return;
        }

        Intent intent = null;
        try {
            LogUtil.d(customNotification.getSerializeIntent());
            intent = Intent.parseUri(customNotification.getSerializeIntent(), 0);
        } catch (URISyntaxException e) {
            LogUtil.d("Intent parseUri failure!!!");
        }

        jumpAppByIntent2(intent);
    }

    /**
     * 通过pendingIntent跳转app.
     *
     * @param pendingIntent
     */
    private static boolean jumpAppByPendingIntent(PendingIntent pendingIntent) {
        LogUtil.d("jumAppByPendingIntent");
        if (pendingIntent == null) {
            return false;
        }

        try {
            pendingIntent.send();
            return true;
        } catch (PendingIntent.CanceledException e) {
            LogUtil.d("jumpAppByPendingIntent Failure!!!");
            return false;
        }
    }

    /**
     * 通过intent跳转app
     *
     * @param intent
     */
    private static void jumpAppByIntent(Intent intent) {
        LogUtil.d("jumpAppByIntent");
        if (intent == null) {
            return;
        }

        try {
            Context context = MyApplication.getContext();
            PackageManager packageManager = context.getPackageManager();

            ResolveInfo resolveInfo = packageManager.resolveActivity(intent, 0);
            if (resolveInfo != null && resolveInfo.activityInfo != null) {
                context.startActivity(intent);
                LogUtil.d("jumpAppByStartActivity");
                return;
            }

            resolveInfo = packageManager.resolveActivity(intent, 0);
            if (resolveInfo != null && resolveInfo.serviceInfo != null) {
                context.startService(intent);
                LogUtil.d("jumpAppByStartService");
                return;
            }

            List broadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
            if (broadcastReceivers != null && broadcastReceivers.size() > 0) {
                context.sendBroadcast(intent);
                LogUtil.d("jumpAppBySendBroadcast");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("jumpAppByIntent Failure!!!");
        }

    }

    /**
     * 通过intent跳转app
     *
     * @param intent
     */
    private static void jumpAppByIntent2(Intent intent) {
        LogUtil.d("jumpAppByIntent");
        if (intent == null) {
            return;
        }

        Context context = MyApplication.getContext();

        try {
            context.startActivity(intent);
            LogUtil.d("jumpAppByStartActivity");
        } catch (Exception e) {
            LogUtil.d("jumpAppByStartActivity Failure!!!");
        }

        try {
            context.startService(intent);
            LogUtil.d("jumpAppByStartService");
        } catch (Exception e) {
            LogUtil.d("jumpAppByStartService Failure!!!");
        }


        try {
            context.sendBroadcast(intent);
            LogUtil.d("jumpAppBySendBroadcast");
        } catch (Exception e) {
            LogUtil.d("jumpAppBySendBroadcast Failure!!!");

        }

    }


}
