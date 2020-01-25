package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Created by son on 2020-01-25.
 */
public class SeekBarStopChangeEvent extends SeekBarChangeEvent {

    public static SeekBarStopChangeEvent create(@NonNull SeekBar seekBar) {
        return new SeekBarStopChangeEvent(seekBar);
    }

    private SeekBarStopChangeEvent(@NonNull SeekBar view) {
        super(view);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SeekBarStopChangeEvent)) return false;
        SeekBarStopChangeEvent other = (SeekBarStopChangeEvent) o;
        return other.view() == view();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + view().hashCode();
        return result;
    }

    @NotNull
    @Override
    public String toString() {
        return "SeekBarStopChangeEvent{view="
                + view()
                + '}';
    }
}
