package com.forceson.rxbinding.widget;

import android.widget.ProgressBar;

import rx.functions.Action1;

/**
 * Created by son on 2020-01-25.
 */
public class RxProgressBar {
    public static Action1<? super Integer> incrementProgressBy(final ProgressBar view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.incrementProgressBy(value);
            }
        };
    }

    public static Action1<? super Integer> incrementSecondaryProgressBy(final ProgressBar view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.incrementSecondaryProgressBy(value);
            }
        };
    }

    public static Action1<? super Boolean> setIndeterminate(final ProgressBar view) {
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setIndeterminate(value);
            }
        };
    }

    public static Action1<? super Integer> setMax(final ProgressBar view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.setMax(value);
            }
        };
    }

    public static Action1<? super Integer> setProgress(final ProgressBar view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.setProgress(value);
            }
        };
    }

    public static Action1<? super Integer> setSecondaryProgress(final ProgressBar view) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                view.setSecondaryProgress(value);
            }
        };
    }
}
