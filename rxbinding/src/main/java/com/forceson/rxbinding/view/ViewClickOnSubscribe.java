package com.forceson.rxbinding.view;

import android.view.View;

import rx.Observable;
import rx.Subscriber;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-09.
 */
class ViewClickOnSubscribe implements Observable.OnSubscribe<Object> {
    private final Object event = new Object();
    private final View view;

    ViewClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super Object> subscriber) {
        checkUiThread();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(event);
                }
            }
        };

        /*subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnClickListener(null);
            }
        });*/

        view.setOnClickListener(listener);
    }
}
