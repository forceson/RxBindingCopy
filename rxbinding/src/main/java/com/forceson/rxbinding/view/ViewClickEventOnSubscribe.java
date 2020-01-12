package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;
import com.forceson.rxbinding.plugins.RxAndroidClockHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-10.
 */
final class ViewClickEventOnSubscribe implements Observable.OnSubscribe<ViewClickEvent> {
    private final View view;

    public ViewClickEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super ViewClickEvent> subscriber) {
        checkUiThread();

        final RxAndroidClockHook clockHook = RxAndroidPlugins.getInstance().getClockHook();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscriber.onNext(ViewClickEvent.create(view, clockHook.uptimeMillis()));
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
