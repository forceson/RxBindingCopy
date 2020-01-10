package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import com.forceson.rxbinding.internal.Functions;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by son on 2020-01-09.
 */
public final class RxView {

    public static Observable<Long> clicks(View view) {
        return Observable.create(new ViewClickOnSubscribe(view));
    }

    public static Observable<ViewClickEvent> clickEvents(View view) {
        return Observable.create(new ViewClickEventOnSubscribe(view));
    }

    public static Observable<DragEvent> drags(View view) {
        return Observable.create(new ViewDragOnSubscribe(view, Functions.ALWAYS_TRUE));
    }

    public static Observable<DragEvent> drags(View view, Func1<DragEvent, Boolean> handled) {
        return Observable.create(new ViewDragOnSubscribe(view, handled));
    }

    public static Observable<ViewDragEvent> dragEvents(View view) {
        return Observable.create(new ViewDragEventOnSubscribe(view, Functions.ALWAYS_TRUE));
    }

    public static Observable<ViewDragEvent> dragEvents(View view,
                                                       Func1<ViewDragEvent, Boolean> handled) {
        return Observable.create(new ViewDragEventOnSubscribe(view, handled));
    }

    public static Observable<Boolean> focusChanges(View view) {
        return Observable.create(new ViewFocusChangeOnSubscribe(view));
    }

    public static Observable<ViewFocusChangeEvent> focusChangeEvents(View view) {
        return Observable.create(new ViewFocusChangeEventOnSubscribe(view));
    }

    public static Observable<Long> longClicks(View view) {
        return Observable.create(new ViewLongClickOnSubscribe(view, Functions.ALWAYS_TRUE));
    }

    public static Observable<Long> longClicks(View view, Func1<? super Long, Boolean> handled) {
        return Observable.create(new ViewLongClickOnSubscribe(view, handled));
    }

    public static Observable<ViewLongClickEvent> longClickEvents(View view) {
        return Observable.create(new ViewLongClickEventOnSubscribe(view, Functions.ALWAYS_TRUE));
    }

    public static Observable<ViewLongClickEvent> longClickEvents(View view,
                                                                 Func1<? super ViewLongClickEvent, Boolean> handled) {
        return Observable.create(new ViewLongClickEventOnSubscribe(view, handled));
    }

    public static Action1<? super Boolean> setActivated(final View view) {
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setActivated(value);
            }
        };
    }

    public static Action1<? super Boolean> setClickable(final View view) {
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setClickable(value);
            }
        };
    }

    public static Action1<? super Boolean> setEnabled(final View view) {
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setEnabled(value);
            }
        };
    }

    public static Action1<? super Boolean> setPressed(final View view) {
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setPressed(value);
            }
        };
    }

    public static Action1<? super Boolean> setSelected(final View view) {
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setSelected(value);
            }
        };
    }

    public static Action1<? super Boolean> setVisibility(View view) {
        return setVisibility(view, View.GONE);
    }

    public static Action1<? super Boolean> setVisibility(final View view,
                                                         final int visibilityWhenFalse) {
        if (visibilityWhenFalse == View.VISIBLE) {
            throw new IllegalArgumentException(
                    "Setting visibility to VISIBLE when false would have no effect.");
        }
        return new Action1<Boolean>() {
            @Override public void call(Boolean value) {
                view.setVisibility(value ? View.VISIBLE : visibilityWhenFalse);
            }
        };
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}
