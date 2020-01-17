package com.forceson.rxbinding.view;

import android.view.View;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewFocusChangeEvent extends ViewEvent<View> {
    public static ViewFocusChangeEvent create(View view, boolean hasFocus) {
        return new ViewFocusChangeEvent(view, hasFocus);
    }

    private final boolean hasFocus;

    private ViewFocusChangeEvent(@NonNull View view, boolean hasFocus) {
        super(view);
        this.hasFocus = hasFocus;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 37 + view().hashCode();
        result = result * 37 + (hasFocus ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViewFocusChangeEvent)) return false;
        ViewFocusChangeEvent other = (ViewFocusChangeEvent) o;
        return other.view() == view() && other.hasFocus == hasFocus;
    }

    @NotNull
    @Override
    public String toString() {
        return "ViewFocusChangeEvent{hasFocus="
                + hasFocus
                + ", view="
                + view()
                + '}';
    }
}
