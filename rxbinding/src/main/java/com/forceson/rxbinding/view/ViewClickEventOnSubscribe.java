package com.forceson.rxbinding.view;

import android.view.View;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(ViewClickEvent.create(view));
                }
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnClickListener(null);
            }
        });

        view.setOnClickListener(listener);
    }
}
