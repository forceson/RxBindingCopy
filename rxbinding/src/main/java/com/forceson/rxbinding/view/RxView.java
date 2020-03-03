package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import com.forceson.rxbinding.internal.Functions;
import com.forceson.rxbinding.internal.Notification;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.forceson.rxbinding.internal.Preconditions.checkArgument;
import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-09.
 */
public final class RxView {

    public static Observable<Object> clicks(View view) {
        checkNotNull(view, "view == null");
        return Observable.create(emitter -> {
            view.setOnClickListener(v -> {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Notification.INSTANCE);
                }
            });
            emitter.setCancellable(() -> view.setOnClickListener(null));
        });
//        return new ViewClickObservable(view);
    }

    public static Observable<DragEvent> drags(View view) {
        checkNotNull(view, "view == null");
        return new ViewDragObservable(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    public static Observable<DragEvent> drags(View view, Function<DragEvent, Boolean> handled) {
        checkNotNull(view, "view == null");
        return new ViewDragObservable(view, handled);
    }

    public static Observable<Boolean> focusChanges(View view) {
        checkNotNull(view, "view == null");
        return new ViewFocusChangeObservable(view);
    }

    public static Observable<Object> longClicks(View view) {
        checkNotNull(view, "view == null");
        return new ViewLongClickObservable(view, Functions.FUNC0_ALWAYS_TRUE);
    }

    public static Observable<Object> longClicks(View view, Callable<Boolean> handled) {
        checkNotNull(view, "view == null");
        checkNotNull(handled, "handled == null");
        return new ViewLongClickObservable(view, handled);
    }

    public static Consumer<? super Boolean> setActivated(final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) throws Exception {
                view.setActivated(value);
            }
        };
    }

    public static Consumer<? super Boolean> setClickable(final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setClickable(value);
            }
        };
    }

    public static Consumer<? super Boolean> setEnabled(final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setEnabled(value);
            }
        };
    }

    public static Consumer<? super Boolean> setPressed(final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setPressed(value);
            }
        };
    }

    public static Consumer<? super Boolean> setSelected(final View view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setSelected(value);
            }
        };
    }

    public static Consumer<? super Boolean> setVisibility(View view) {
        checkNotNull(view, "view == null");
        return setVisibility(view, View.GONE);
    }

    public static Consumer<? super Boolean> setVisibility(final View view,
                                                         final int visibilityWhenFalse) {
        checkNotNull(view, "view == null");
        checkArgument(visibilityWhenFalse != View.VISIBLE,
                "Setting visibility to VISIBLE when false would have no effect.");
        checkArgument(visibilityWhenFalse == View.INVISIBLE || visibilityWhenFalse == View.GONE,
                "Must set visibility to INVISIBLE or GONE when false.");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setVisibility(value ? View.VISIBLE : visibilityWhenFalse);
            }
        };
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}
