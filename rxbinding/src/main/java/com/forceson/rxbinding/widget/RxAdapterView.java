package com.forceson.rxbinding.widget;

import android.widget.AdapterView;

import com.forceson.rxbinding.internal.Functions;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by son on 2020-01-17.
 */
public final class RxAdapterView {

    public static Observable<Integer> itemLongClicks(AdapterView<?> view) {
        return itemLongClicks(view, Functions.FUNC0_ALWAYS_TRUE);
    }

    private static Observable<Integer> itemLongClicks(AdapterView<?> view, Callable<Boolean> handled) {
        return new AdapterViewItemLongClickObservable(view, handled);
    }

    public static Observable<Integer> itemSelections(AdapterView<?> view) {
        return new AdapterViewItemSelectionObservable(view);
    }

    public static Observable<AdapterViewSelectionEvent> selectionEvents(AdapterView<?> view) {
        return new AdapterViewSelectionObservable(view);
    }

    public static Consumer<Integer> setSelection(final AdapterView<?> view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.setSelection(value);
            }
        };
    }

    private RxAdapterView() {
        throw new AssertionError("No instances.");
    }
}
