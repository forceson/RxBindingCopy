package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func0;

/**
 * Created by son on 2020-01-24.
 */
public class AdapterViewLongClickOnSubscribe implements Observable.OnSubscribe<Integer> {
    private final AdapterView<?> view;
    private final Func0<Boolean> handled;

    public AdapterViewLongClickOnSubscribe(AdapterView<?> view, Func0<Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (handled.call()) {
                    subscriber.onNext(position);
                    return true;
                }
                return false;
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnItemLongClickListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnItemLongClickListener(listener);
    }
}
