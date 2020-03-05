package com.forceson.rxbinding.widget;


import android.widget.RadioGroup;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by son on 2020-01-25.
 */
public class RxRadioGroup {
    public static Observable<Integer> checkedChanges(RadioGroup view) {
        return new RadioGroupCheckedChangeObservable(view);
    }

    public static Consumer<Integer> setChecked(final RadioGroup view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                if (value == -1) {
                    view.clearCheck();
                } else {
                    view.check(value);
                }
            }
        };
    }
}
