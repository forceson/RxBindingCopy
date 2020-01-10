package com.forceson.rxbinding.view;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewClickEvent extends ViewEvent<View> {
    public static ViewClickEvent create(View view, long timestamp) {
        return new ViewClickEvent(view, timestamp);
    }

    private ViewClickEvent(@NonNull View view, long timestamp) {
        super(view, timestamp);
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewClickEvent{view=" + view() + ", timestamp=" + timestamp() + '}';
    }
}
