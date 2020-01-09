package com.forceson.rxbinding.internal;

import android.os.Looper;

import com.forceson.rxbinding.schedulers.HandlerSchedulers;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by son on 2020-01-09.
 */
public final class AndroidSubscriptions {

    public static Subscription unsubscribeOnMainThread(final Action0 unsubscribe) {
        return Subscriptions.create(new Action0() {
            @Override
            public void call() {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    unsubscribe.call();
                } else {
                    final Scheduler.Worker inner = HandlerSchedulers.mainThread().createWorker();
                    inner.schedule(new Action0() {
                        @Override
                        public void call() {
                            unsubscribe.call();
                            inner.unsubscribe();
                        }
                    });
                }
            }
        });
    }

    private AndroidSubscriptions() {
        throw new AssertionError("No instances.");
    }
}
