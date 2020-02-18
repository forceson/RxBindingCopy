package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-24.
 */
public class AdapterViewItemClickEventOnSubscribe implements Observable.OnSubscribe<AdapterViewItemClickEvent> {
    private final AdapterView<?> view;

    public AdapterViewItemClickEventOnSubscribe(AdapterView<?> view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super AdapterViewItemClickEvent> subscriber) {
        checkUiThread();

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(AdapterViewItemClickEvent.create(adapterView, view, position, id));
                }
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnItemClickListener(null);
            }
        });

        view.setOnItemClickListener(listener);
    }
}
