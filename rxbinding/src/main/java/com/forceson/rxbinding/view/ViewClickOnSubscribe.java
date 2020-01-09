package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;
import com.forceson.rxbinding.plugins.RxAndroidClockHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Assertions.assertUiThread;

/**
 * Created by son on 2020-01-09.
 */
class ViewClickOnSubscribe implements Observable.OnSubscribe<Long> {
    private final View view;

    ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super Long> subscriber) {
        assertUiThread();

        final RxAndroidClockHook clockHook = RxAndroidPlugins.getInstance().getClockHook();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscriber.onNext(clockHook.upTimeMillis());
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnClickListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnClickListener(listener);
    }
}
