package com.forceson.rxbinding.view;

import android.view.View;

import androidx.annotation.NonNull;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewEvent<T extends View> {
    @NonNull
    private final T view;

    protected ViewEvent(@NonNull T view) {
        this.view = checkNotNull(view, "view == null");
    }

    public @NonNull
    T view() {
        return view;
    }
}
