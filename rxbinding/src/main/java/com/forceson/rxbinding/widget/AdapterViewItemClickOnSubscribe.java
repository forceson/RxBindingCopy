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
 * Created by son on 2020-01-24.
 */
public class AdapterViewItemClickOnSubscribe implements Observable.OnSubscribe<Integer> {
    private final AdapterView<?> view;

    public AdapterViewItemClickOnSubscribe(AdapterView<?> view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        checkUiThread();

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(position);
                }
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnItemClickListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnItemClickListener(listener);
    }
}
