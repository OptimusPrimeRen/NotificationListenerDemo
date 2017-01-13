package com.rentee.notificationlistenerdemo.core;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;

import com.rentee.notificationlistenerdemo.bean.CustomNotification;
import com.rentee.notificationlistenerdemo.util.AppUtils;
import com.rentee.notificationlistenerdemo.util.LogUtil;

/**
 * Created by rentee on 17-1-10.
 * 通知栏监听service
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationMonitor extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        LogUtil.d("Notification Posted");

        //获取PendingItent
        PendingIntent pendingIntent = sbn.getNotification().contentIntent;

        Intent intent = AppUtils.getIntentByPendingIntent(pendingIntent);

        //将Intent转换成String，可以存到数据库
        String serializeIntent = intent.toUri(0);

        CustomNotification customNotification = new CustomNotification();
//        customNotification.setPendingIntent(pendingIntent);
        customNotification.setSerializeIntent(serializeIntent);

        //跳转逻辑
        JumpAppLogic.jumpAppByNotification(customNotification);

        // TODO: 17-1-13 存储通知栏通知视图
//        RemoteViews remoteView  = sbn.getNotification().contentView;
//        remoteView.apply(getApplicationContext(),);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        LogUtil.d("Notification Removed");
    }

}
