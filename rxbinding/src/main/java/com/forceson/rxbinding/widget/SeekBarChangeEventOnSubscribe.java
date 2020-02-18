package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import com.forceson.rxbinding.internal.AndroidSubscriptions;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-25.
 */
public class SeekBarChangeEventOnSubscribe implements Observable.OnSubscribe<SeekBarChangeEvent> {
    private final SeekBar view;

    public SeekBarChangeEventOnSubscribe(SeekBar view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super SeekBarChangeEvent> subscriber) {
        checkUiThread();

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(SeekBarProgressChangeEvent.create(seekBar, progress, fromUser));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(SeekBarStartChangeEvent.create(seekBar));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(SeekBarStopChangeEvent.create(seekBar));
                }
            }
        };

        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnSeekBarChangeListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnSeekBarChangeListener(listener);
    }
}
