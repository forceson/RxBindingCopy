package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-10.
 */
final class ViewFocusChangeOnSubscribe implements Observable.OnSubscribe<Boolean> {
    private final View view;

    public ViewFocusChangeOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super Boolean> subscriber) {
        checkUiThread();

        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                subscriber.onNext(hasFocus);
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
        subscriber.onNext(view.hasFocus());
    }
}
