package com.forceson.rxbinding.widget;

import android.widget.ProgressBar;

import io.reactivex.functions.Consumer;

/**
 * Created by son on 2020-01-25.
 */
public class RxProgressBar {
    public static Consumer<? super Integer> incrementProgressBy(final ProgressBar view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.incrementProgressBy(value);
            }
        };
    }

    public static Consumer<? super Integer> incrementSecondaryProgressBy(final ProgressBar view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.incrementSecondaryProgressBy(value);
            }
        };
    }

    public static Consumer<? super Boolean> setIndeterminate(final ProgressBar view) {
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) {
                view.setIndeterminate(value);
            }
        };
    }

    public static Consumer<? super Integer> setMax(final ProgressBar view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.setMax(value);
            }
        };
    }

    public static Consumer<? super Integer> setProgress(final ProgressBar view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.setProgress(value);
            }
        };
    }

    public static Consumer<? super Integer> setSecondaryProgress(final ProgressBar view) {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                view.setSecondaryProgress(value);
            }
        };
    }
}
