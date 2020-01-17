package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-10.
 */

final class ViewLongClickOnSubscribe implements Observable.OnSubscribe<Object> {
    private final Object event = new Object();
    private final View view;
    private final Func0<Boolean> handled;

    ViewLongClickOnSubscribe(View view, Func0<Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override public void call(final Subscriber<? super Object> subscriber) {
        checkUiThread();

        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override public boolean onLongClick(View v) {
                if (handled.call()) {
                    subscriber.onNext(event);
                    return true;
                }
                return false;
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override public void call() {
                view.setOnLongClickListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnLongClickListener(listener);
    }
}
