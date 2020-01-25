package com.forceson.rxbinding.widget;

import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.forceson.rxbinding.view.ViewEvent;

/**
 * Created by son on 2020-01-25.
 */
public class RadioGroupCheckedChangeEvent extends ViewEvent<RadioGroup> {
    public static RadioGroupCheckedChangeEvent create(RadioGroup radioGroup, int checkedId) {
        return new RadioGroupCheckedChangeEvent(radioGroup, checkedId);
    }

    private final int checkedId;

    private RadioGroupCheckedChangeEvent(@NonNull RadioGroup view, int checkedId) {
        super(view);
        this.checkedId = checkedId;
    }

    public int checkedId() {
        return checkedId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RadioGroupCheckedChangeEvent)) return false;
        RadioGroupCheckedChangeEvent other = (RadioGroupCheckedChangeEvent) o;
        return other.view() == view() && other.checkedId == checkedId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + view().hashCode();
        result = result * 37 + checkedId;
        return result;
    }

    @Override
    public String toString() {
        return "RadioGroupCheckedChangeEvent{view=" + view() + ", checkedId=" + checkedId + '}';
    }
}
