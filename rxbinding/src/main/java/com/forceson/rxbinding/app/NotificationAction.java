package com.forceson.rxbinding.app;

import android.app.Notification;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by son on 2020-01-11.
 */
public abstract class NotificationAction {
    private NotificationAction() {

    }

    public abstract void apply(@NonNull NotificationManager notificationManager);

    public static final class ShowNotification extends NotificationAction {

        public static ShowNotification create(int id, Notification notification) {
            return new ShowNotification(null, id, notification);
        }

        public static ShowNotification create(@Nullable String tag, int id, Notification notification) {
            return new ShowNotification(tag, id, notification);
        }

        private final int id;
        private final String tag;
        private final Notification notification;

        private ShowNotification(@Nullable String tag, int id, Notification notification) {
            this.id = id;
            this.tag = tag;
            this.notification = notification;
        }

        public int id() {
            return id;
        }

        @Nullable
        public String tag() {
            return tag;
        }

        public Notification notification() {
            return notification;
        }

        @Override
        public void apply(@NonNull NotificationManager notificationManager) {
            notificationManager.notify(tag, id, notification);
        }
    }

    public static final class CancelNotification extends NotificationAction {
        public static CancelNotification create(int id) {
            return new CancelNotification(null, id);
        }

        public static CancelNotification create(@Nullable String tag, int id) {
            return new CancelNotification(tag, id);
        }

        private final int id;
        private final String tag;

        private CancelNotification(@Nullable String tag, int id) {
            this.id = id;
            this.tag = tag;
        }

        public int id() {
            return id;
        }

        @Nullable
        public String tag() {
            return tag;
        }

        @Override
        public void apply(@NonNull NotificationManager notificationManager) {
            notificationManager.cancel(tag, id);
        }
    }

    public static final class CancelAllNotifications extends NotificationAction {
        public static CancelAllNotifications create() {
            return new CancelAllNotifications();
        }

        private CancelAllNotifications() {
        }

        @Override public void apply(@NonNull NotificationManager notificationManager) {
            notificationManager.cancelAll();
        }
    }
}
