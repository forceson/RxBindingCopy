package com.forceson.rxbinding.widget;

import android.widget.CompoundButton;

import rx.Observable;
import rx.functions.Action1;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-11.
 */
public final class RxCompoundButton {

    public static Observable<Boolean> checkedChanges(CompoundButton view) {
        checkNotNull(view, "view == null");
        return Observable.create(new CompoundButtonCheckedChangeOnSubscribe(view));
    }

    public static Observable<CompoundButtonCheckedChangeEvent> checkedChangeEvents(
            CompoundButton view) {
        checkNotNull(view, "view == null");
        return Observable.create(new CompoundButtonCheckedChangeEventOnSubscribe(view));
    }

    public static Action1<? super Boolean> setChecked(final CompoundButton view) {
        checkNotNull(view, "view == null");
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setChecked(value);
            }
        };
    }

    public static Action1<? super Object> toggle(final CompoundButton view) {
        checkNotNull(view, "view == null");
        return new Action1<Object>() {
            @Override
            public void call(Object value) {
                view.toggle();
            }
        };
    }

    private RxCompoundButton() {
        throw new AssertionError("No instances.");
    }
}
