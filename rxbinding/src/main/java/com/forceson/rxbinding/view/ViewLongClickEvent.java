package com.forceson.rxbinding.view;

import android.view.View;

/**
 * Created by son on 2020-01-10.
 */
public final class ViewLongClickEvent extends ViewEvent<View> {
    public static ViewLongClickEvent create(View view, long timestamp) {
        return new ViewLongClickEvent(view, timestamp);
    }

    private ViewLongClickEvent(View view, long timestamp) {
        super(view, timestamp);
    }

    @Override
    public String toString() {
        return "ViewLongClickEvent{view=" + view() + ", timestamp=" + timestamp() + '}';
    }
}