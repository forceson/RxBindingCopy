package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.forceson.rxbinding.view.ViewEvent;

/**
 * Created by son on 2020-01-25.
 */
public class SeekBarChangeEvent extends ViewEvent<SeekBar> {
    SeekBarChangeEvent(@NonNull SeekBar view) {
        super(view);
    }
}
