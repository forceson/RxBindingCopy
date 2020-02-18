package com.forceson.rxbinding.widget;

import android.widget.RadioGroup;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

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
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(checkedId);
                }
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnCheckedChangeListener(null);
            }
        });

        view.setOnCheckedChangeListener(listener);
    }
}
