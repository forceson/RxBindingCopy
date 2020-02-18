package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-17.
 */
public class AdapterViewItemSelectionOnSubscribe implements Observable.OnSubscribe<Integer> {

    final AdapterView<?> view;

    public AdapterViewItemSelectionOnSubscribe(AdapterView<?> view) {
        this.view = view;
    }


    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        checkUiThread();

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnItemSelectedListener(null);
            }
        });

        view.setOnItemSelectedListener(listener);
    }
}
