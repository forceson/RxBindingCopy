package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import io.reactivex.Observable;

/**
 * Created by son on 2020-01-25.
 */
public class RxSeekBar {
    public static Observable<Integer> changes(SeekBar view) {
        return new SeekBarChangeObservable(view);
    }

    public static Observable<SeekBarChangeEvent> changeEvents(SeekBar view) {
        return new SeekBarChangeEventObservable(view);
    }

    private RxSeekBar() {
        throw new AssertionError("No instances.");
    }
}
