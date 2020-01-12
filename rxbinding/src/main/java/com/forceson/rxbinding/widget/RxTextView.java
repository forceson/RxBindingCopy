package com.forceson.rxbinding.widget;

import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public final class RxTextView {
    public static Observable<CharSequence> textChanges(TextView view) {
        checkNotNull(view, "view == null");
        return Observable.create(new TextViewTextOnSubscribe(view));
    }

    public static Observable<TextViewTextChangeEvent> textChangeEvents(TextView view) {
        checkNotNull(view, "view == null");
        return Observable.create(new TextViewTextEventOnSubscribe(view));
    }

    public static Action1<? super CharSequence> setText(final TextView view) {
        checkNotNull(view, "view == null");
        return new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                view.setText(charSequence);
            }
        };
    }

    public static Action1<? super Integer> setTextRes(final TextView view) {
        checkNotNull(view, "view == null");
        return new Action1<Integer>() {
            @Override
            public void call(Integer textRes) {
                view.setText(textRes);
            }
        };
    }

    private RxTextView() {
        throw new AssertionError("No instances.");
    }
}