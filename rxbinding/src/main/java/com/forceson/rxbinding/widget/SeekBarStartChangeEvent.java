package com.forceson.rxbinding.widget;

import android.widget.SeekBar;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Created by son on 2020-01-25.
 */
public class SeekBarStartChangeEvent extends SeekBarChangeEvent {

    public static SeekBarStartChangeEvent create(@NonNull SeekBar seekBar) {
        return new SeekBarStartChangeEvent(seekBar);
    }

    private SeekBarStartChangeEvent(@NonNull SeekBar view) {
        super(view);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SeekBarStartChangeEvent)) return false;
        SeekBarStartChangeEvent other = (SeekBarStartChangeEvent) o;
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
        return "SeekBarStartChangeEvent{view="
                + view()
                + '}';
    }
}
