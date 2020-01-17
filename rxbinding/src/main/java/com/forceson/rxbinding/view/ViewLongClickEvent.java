package com.forceson.rxbinding.view;

import android.view.View;

/**
 * Created by son on 2020-01-10.
 */
public final class ViewLongClickEvent extends ViewEvent<View> {
    public static ViewLongClickEvent create(View view) {
        return new ViewLongClickEvent(view);
    }

    private ViewLongClickEvent(View view) {
        super(view);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViewLongClickEvent)) return false;
        ViewLongClickEvent other = (ViewLongClickEvent) o;
        return other.view() == view();
    }

    @Override
    public int hashCode() {
        return view().hashCode();
    }

    @Override
    public String toString() {
        return "ViewLongClickEvent{view=" + view() + '}';
    }
}