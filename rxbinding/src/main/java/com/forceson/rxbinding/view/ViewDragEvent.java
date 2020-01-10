package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

/**
 * Created by son on 2020-01-10.
 */
public class ViewDragEvent extends ViewEvent<View> {
    public static ViewDragEvent create(View view, long timestamp, DragEvent dragEvent) {
        return new ViewDragEvent(view, timestamp, dragEvent);
    }

    private final DragEvent dragEvent;

    private ViewDragEvent(View view, long timestamp, DragEvent dragEvent) {
        super(view, timestamp);
        this.dragEvent = dragEvent;
    }

    public DragEvent dragEvent() {
        return dragEvent;
    }

    // TODO hashCode
    // TODO equals
    // TODO toString
}
