package com.forceson.rxbinding.widget;

import android.widget.RadioGroup;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-25.
 */
public class RadioGroupCheckedChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    private final RadioGroup view;

    public RadioGroupCheckedChangeOnSubscribe(RadioGroup view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        checkUiThread();

        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                subscriber.onNext(checkedId);
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnCheckedChangeListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnCheckedChangeListener(listener);
    }
}
