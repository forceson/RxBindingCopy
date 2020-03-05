package com.forceson.rxbinding.widget;

import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public final class RxTextView {
    public static Observable<CharSequence> textChanges(TextView view) {
        checkNotNull(view, "view == null");
        return new TextViewTextObservable(view);
    }

    public static Observable<TextViewTextChangeEvent> textChangeEvents(TextView view) {
        checkNotNull(view, "view == null");
        return new TextViewTextChangeEventObservable(view);
    }

    public static Consumer<? super CharSequence> setText(final TextView view) {
        checkNotNull(view, "view == null");
        return new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) {
                view.setText(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> setTextRes(final TextView view) {
        checkNotNull(view, "view == null");
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer textRes) {
                view.setText(textRes);
            }
        };
    }

    private RxTextView() {
        throw new AssertionError("No instances.");
    }
}
