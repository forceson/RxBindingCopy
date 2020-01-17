package com.forceson.rxbinding.view;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewClickEvent extends ViewEvent<View> {
    public static ViewClickEvent create(View view) {
        return new ViewClickEvent(view);
    }

    private ViewClickEvent(@NonNull View view) {
        super(view);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViewClickEvent)) return false;
        ViewClickEvent other = (ViewClickEvent) o;
        return other.view() == view();
    }

    @Override
    public int hashCode() {
        return view().hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewClickEvent{view=" + view() + '}';
    }
}
