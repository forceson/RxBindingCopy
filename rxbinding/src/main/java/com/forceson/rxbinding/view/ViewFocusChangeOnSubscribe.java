package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

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
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(hasFocus);
                }
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnFocusChangeListener(null);
            }
        });

        view.setOnFocusChangeListener(listener);

        // Send out the initial value.
        subscriber.onNext(view.hasFocus());
    }
}
