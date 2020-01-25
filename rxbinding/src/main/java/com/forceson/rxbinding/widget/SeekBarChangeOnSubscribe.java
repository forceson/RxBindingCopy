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
public class SeekBarChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
    private final SeekBar view;

    public SeekBarChangeOnSubscribe(SeekBar view) {
        this.view = view;
    }

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        checkUiThread();

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                subscriber.onNext(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
