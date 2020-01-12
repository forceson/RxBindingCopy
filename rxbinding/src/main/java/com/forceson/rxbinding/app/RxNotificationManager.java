package com.forceson.rxbinding.app;

import android.app.NotificationManager;

import rx.functions.Action1;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-11.
 */
public class RxNotificationManager {
    public static Action1<? extends NotificationAction> performAction(
            final NotificationManager notificationManager) {
        checkNotNull(notificationManager, "notificationManager == null");
        return new Action1<NotificationAction>() {
            @Override
            public void call(NotificationAction notificationAction) {
                notificationAction.apply(notificationManager);
            }
        };
    }
}
