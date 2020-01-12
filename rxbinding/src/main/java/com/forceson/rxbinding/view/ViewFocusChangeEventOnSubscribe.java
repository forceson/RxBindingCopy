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
public class ViewFocusChangeEventOnSubscribe implements Observable.OnSubscribe<ViewFocusChangeEvent> {
    private final View view;

    public ViewFocusChangeEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super ViewFocusChangeEvent> subscriber) {
        checkUiThread();

        final RxAndroidClockHook clockHook = RxAndroidPlugins.getInstance().getClockHook();
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                subscriber.onNext(ViewFocusChangeEvent.create(view, clockHook.uptimeMillis(), hasFocus));
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnFocusChangeListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnFocusChangeListener(listener);

        // Send out the initial value.
        subscriber.onNext(ViewFocusChangeEvent.create(view, clockHook.uptimeMillis(), view.hasFocus()));
    }
}
