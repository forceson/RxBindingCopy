package com.forceson.rxbinding.widget;

import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.forceson.rxbinding.view.ViewEvent;

/**
 * Created by son on 2020-01-17.
 */
public class AdapterViewSelectionEvent extends ViewEvent<AdapterView<?>> {
    protected AdapterViewSelectionEvent(@NonNull AdapterView<?> view) {
        super(view);
    }
}
