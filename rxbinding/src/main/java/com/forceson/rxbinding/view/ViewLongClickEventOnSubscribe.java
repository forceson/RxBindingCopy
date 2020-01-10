package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;
import com.forceson.rxbinding.plugins.RxAndroidClockHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

import static com.forceson.rxbinding.internal.Assertions.assertUiThread;

/**
 * Created by son on 2020-01-10.
 */
final class ViewLongClickEventOnSubscribe implements Observable.OnSubscribe<ViewLongClickEvent> {
    private final View view;
    private final Func1<? super ViewLongClickEvent, Boolean> handled;

    ViewLongClickEventOnSubscribe(View view, Func1<? super ViewLongClickEvent, Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    public void call(final Subscriber<? super ViewLongClickEvent> subscriber) {
        assertUiThread();

        final RxAndroidClockHook clockHook = RxAndroidPlugins.getInstance().getClockHook();
        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ViewLongClickEvent event = ViewLongClickEvent.create(view, clockHook.uptimeMillis());
                if (handled.call(event)) {
                    subscriber.onNext(event);
                    return true;
                }
                return false;
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnLongClickListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnLongClickListener(listener);
    }
}