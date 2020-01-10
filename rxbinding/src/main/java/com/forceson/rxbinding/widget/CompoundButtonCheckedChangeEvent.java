package com.forceson.rxbinding.widget;

import android.widget.CompoundButton;

import com.forceson.rxbinding.view.ViewEvent;

/**
 * Created by son on 2020-01-10.
 */
public final class CompoundButtonCheckedChangeEvent extends ViewEvent<CompoundButton> {
    public static CompoundButtonCheckedChangeEvent create(CompoundButton view, long timestamp,
                                                          boolean isChecked) {
        return new CompoundButtonCheckedChangeEvent(view, timestamp, isChecked);
    }

    private final boolean isChecked;

    private CompoundButtonCheckedChangeEvent(CompoundButton view, long timestamp, boolean isChecked) {
        super(view, timestamp);
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 37 + (isChecked ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CompoundButtonCheckedChangeEvent)) return false;
        CompoundButtonCheckedChangeEvent other = (CompoundButtonCheckedChangeEvent) o;
        return super.equals(other)
                && isChecked == other.isChecked;
    }

    @Override
    public String toString() {
        return "CompoundButtonCheckedChangeEvent{isChecked="
                + isChecked
                + ", view="
                + view()
                + ", timestamp="
                + timestamp()
                + '}';
    }
}