package com.forceson.rxbinding.widget;


import android.widget.RadioGroup;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by son on 2020-01-25.
 */
public class RxRadioGroup {
    public static Observable<Integer> checkedChange(RadioGroup view) {
        return Observable.create(new RadioGroupCheckedChangeOnSubscribe(view));
    }

    public static Observable<RadioGroupCheckedChangeEvent> checkedChangeEvents(RadioGroup view) {
        return Observable.create(new RadioGroupCheckedChangeEventOnSubscribe(view));
    }

    public static Action1<Integer> setChecked(final RadioGroup view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                if (value == -1) {
                    view.clearCheck();
                } else {
                    view.check(value);
                }
            }
        };
    }
}
