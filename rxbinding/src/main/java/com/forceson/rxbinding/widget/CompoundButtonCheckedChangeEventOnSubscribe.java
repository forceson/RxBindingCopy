package com.forceson.rxbinding.widget;

import android.widget.CompoundButton;

import com.forceson.rxbinding.internal.AndroidSubscriptions;
import com.forceson.rxbinding.plugins.RxAndroidClockHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Assertions.assertUiThread;

/**
 * Created by son on 2020-01-10.
 */
final class CompoundButtonCheckedChangeEventOnSubscribe
        implements Observable.OnSubscribe<CompoundButtonCheckedChangeEvent> {
    private final CompoundButton view;

    public CompoundButtonCheckedChangeEventOnSubscribe(CompoundButton view) {
        this.view = view;
    }

    @Override
    public void call(
            final Subscriber<? super CompoundButtonCheckedChangeEvent> subscriber) {
        assertUiThread();

        final RxAndroidClockHook clockHook = RxAndroidPlugins.getInstance().getClockHook();
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subscriber.onNext(
                        CompoundButtonCheckedChangeEvent.create(view, clockHook.uptimeMillis(), isChecked));
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnCheckedChangeListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnCheckedChangeListener(listener);

        // Send out the initial value.
        subscriber.onNext(
                CompoundButtonCheckedChangeEvent.create(view, clockHook.uptimeMillis(), view.isChecked()));
    }
}
