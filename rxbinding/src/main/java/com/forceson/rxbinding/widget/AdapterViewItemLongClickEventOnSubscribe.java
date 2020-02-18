package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by son on 2020-01-24.
 */
public class AdapterViewItemLongClickEventOnSubscribe implements Observable.OnSubscribe<AdapterViewItemLongClickEvent> {
    final AdapterView<?> view;
    private final Func1<? super AdapterViewItemLongClickEvent, Boolean> handled;

    public AdapterViewItemLongClickEventOnSubscribe(AdapterView<?> view,
                                                    Func1<? super AdapterViewItemLongClickEvent, Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    public void call(Subscriber<? super AdapterViewItemLongClickEvent> subscriber) {
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                AdapterViewItemLongClickEvent event = AdapterViewItemLongClickEvent.create(adapterView, view, position, id);
                if (handled.call(event)) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(event);
                    }
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
