package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import rx.Observable;

/**
 * Created by son on 2020-01-25.
 */
public class RxSeekBar {
    public static Observable<Integer> changes(SeekBar view) {
        return Observable.create(new SeekBarChangeOnSubscribe(view));
    }

    public static Observable<SeekBarChangeEvent> changeEvents(SeekBar view) {
        return Observable.create(new SeekBarChangeEventOnSubscribe(view));
    }

    private RxSeekBar() {
        throw new AssertionError("No instances.");
    }
}
