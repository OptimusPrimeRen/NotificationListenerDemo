package com.rentee.notificationlistenerdemo.util;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by rentee on 17-1-13.
 */

public class AppUtils {


    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    /**
     * 跳转到通知栏监听管理界面
     */
    public static void gotoNotificationManager(Context context) {
        context.startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }

    /**
     * 判断通知栏监听权限是否打开
     * @param context
     * @return
     */
    public static boolean isNotificationManagerEnabled(Context context) {
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (String name : names) {
                final ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 通过反射从PendingIntent中拿到Intent
     *
     * @param pendingIntent
     * @return intent
     */
    public static Intent getIntentByPendingIntent(PendingIntent pendingIntent) {
        Intent intent = null;
        try {
            intent = (Intent) PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(pendingIntent, new Object[0]);
        } catch (Exception paramPendingIntent) {
            LogUtil.d("PendingIntent getIntent failure!!!");
        }
        return intent;
    }

}
