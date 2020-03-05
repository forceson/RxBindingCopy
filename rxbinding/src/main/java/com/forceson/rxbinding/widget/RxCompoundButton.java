package com.forceson.rxbinding.widget;

import android.widget.CompoundButton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-11.
 */
public final class RxCompoundButton {

    public static Observable<Boolean> checkedChanges(CompoundButton view) {
        checkNotNull(view, "view == null");
        return new CompoundButtonCheckedChangeObservable(view);
    }

    public static Consumer<? super Boolean> setChecked(final CompoundButton view) {
        checkNotNull(view, "view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setChecked(value);
            }
        };
    }

    public static Consumer<? super Object> toggle(final CompoundButton view) {
        checkNotNull(view, "view == null");
        return new Consumer<Object>() {
            @Override
            public void accept(Object value) {
                view.toggle();
            }
        };
    }

    private RxCompoundButton() {
        throw new AssertionError("No instances.");
    }
}
