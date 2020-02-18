package com.forceson.rxbinding.widget;

import android.widget.RadioGroup;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-25.
 */
public class RadioGroupCheckedChangeEventOnSubscribe implements Observable.OnSubscribe<RadioGroupCheckedChangeEvent> {
    private final RadioGroup view;

    public RadioGroupCheckedChangeEventOnSubscribe(RadioGroup view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super RadioGroupCheckedChangeEvent> subscriber) {
        checkUiThread();

        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(RadioGroupCheckedChangeEvent.create(radioGroup, checkedId));
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
