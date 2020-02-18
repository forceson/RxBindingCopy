package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-17.
 */

final class AdapterViewSelectionOnSubscribe
        implements Observable.OnSubscribe<AdapterViewSelectionEvent> {
    private final AdapterView<?> view;

    public AdapterViewSelectionOnSubscribe(AdapterView<?> view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super AdapterViewSelectionEvent> subscriber) {
        checkUiThread();

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(AdapterViewItemSelectionEvent.create(parent, view, position, id));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(AdapterViewNothingSelectionEvent.create(parent));
                }
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnItemSelectedListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnItemSelectedListener(listener);
    }
}
