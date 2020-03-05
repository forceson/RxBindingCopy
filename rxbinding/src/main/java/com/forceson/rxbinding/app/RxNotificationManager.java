package com.forceson.rxbinding.app;

import android.app.NotificationManager;

import io.reactivex.functions.Consumer;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-11.
 */
public class RxNotificationManager {
    public static Consumer<? extends NotificationAction> performAction(
            final NotificationManager notificationManager) {
        checkNotNull(notificationManager, "notificationManager == null");
        return new Consumer<NotificationAction>() {
            @Override
            public void accept(NotificationAction notificationAction) {
                notificationAction.apply(notificationManager);
            }
        };
    }
}
