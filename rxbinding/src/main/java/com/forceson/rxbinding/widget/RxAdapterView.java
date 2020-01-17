package com.forceson.rxbinding.widget;

import android.widget.AdapterView;

import rx.Observable;
import rx.functions.Action1;


/**
 * Created by son on 2020-01-17.
 */
public final class RxAdapterView {

    public static Observable<Integer> itemSelections(AdapterView<?> view) {
        return Observable.create(new AdapterViewItemSelectionOnSubscribe(view));
    }

    public static Observable<AdapterViewSelectionEvent> selectionEvents(AdapterView<?> view) {
        return Observable.create(new AdapterViewSelectionOnSubscribe(view));
    }

    public static Action1<Integer> setSelection(final AdapterView<?> view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.setSelection(value);
            }
        };
    }

    private RxAdapterView() {
        throw new AssertionError("No instances.");
    }
}
