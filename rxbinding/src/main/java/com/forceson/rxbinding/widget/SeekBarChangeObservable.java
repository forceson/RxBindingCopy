package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

/**
 * Created by son on 2020-01-25.
 */
public class SeekBarChangeObservable extends Observable<Integer> {
    private final SeekBar view;

    SeekBarChangeObservable(SeekBar view) {
        this.view = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        verifyMainThread();
        Listener listener = new Listener(view, observer);
        observer.onSubscribe(listener);
        view.setOnSeekBarChangeListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements SeekBar.OnSeekBarChangeListener {
        private final SeekBar view;
        private final Observer<? super Integer> observer;

        Listener(SeekBar view, Observer<? super Integer> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!isDisposed()) {
                observer.onNext(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        protected void onDispose() {
            view.setOnSeekBarChangeListener(null);
        }
    }
}
