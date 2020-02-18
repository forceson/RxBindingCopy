package com.forceson.rxbinding;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import rx.Subscription;

/**
 * Created by son on 2020-02-18.
 */
public abstract class MainThreadSubscription implements Subscription, Runnable {
    private static final Handler mainThread = new Handler(Looper.getMainLooper());

    private volatile int unsubscribed;
    private static final AtomicIntegerFieldUpdater<MainThreadSubscription> unsubscribedUpdater =
            AtomicIntegerFieldUpdater.newUpdater(MainThreadSubscription.class, "unsubscribed");

    @Override
    public void run() {
        onUnsubscribe();
    }

    @Override
    public void unsubscribe() {
        if (unsubscribedUpdater.compareAndSet(this, 0, 1)) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                onUnsubscribe();
            } else {
                mainThread.post(this);
            }
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return unsubscribed != 0;
    }

    protected abstract void onUnsubscribe();
}
