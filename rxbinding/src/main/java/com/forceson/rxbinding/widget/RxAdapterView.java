package com.forceson.rxbinding.widget;

import android.widget.AdapterView;

import com.forceson.rxbinding.internal.Functions;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;


/**
 * Created by son on 2020-01-17.
 */
public final class RxAdapterView {

    public static Observable<Integer> itemClicks(AdapterView<?> view) {
        return Observable.create(new AdapterViewItemClickOnSubscribe(view));
    }

    public static Observable<AdapterViewItemClickEvent> itemClickEvents(AdapterView<?> view) {
        return Observable.create(new AdapterViewItemClickEventOnSubscribe(view));
    }

    public static Observable<Integer> itemLongClicks(AdapterView<?> view) {
        return itemLongClicks(view, Functions.FUNC0_ALWAYS_TRUE);
    }

    private static Observable<Integer> itemLongClicks(AdapterView<?> view, Func0<Boolean> handled) {
        return Observable.create(new AdapterViewLongClickOnSubscribe(view, handled));
    }

    public static Observable<AdapterViewItemLongClickEvent> itemLongClicksEvents(AdapterView<?> view) {
        return itemLongClicksEvents(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    private static Observable<AdapterViewItemLongClickEvent> itemLongClicksEvents(AdapterView<?> view, Func1<? super AdapterViewItemLongClickEvent, Boolean> handled) {
        return Observable.create(new AdapterViewItemLongClickEventOnSubscribe(view, handled));
    }

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
