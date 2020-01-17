package com.forceson.rxbinding.internal;

import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by son on 2020-01-10.
 */
public class Functions {
    public static Always<Boolean> ALWAYS_TRUE = new Always<>(true);
    public static final Func0<Boolean> FUNC0_ALWAYS_TRUE = ALWAYS_TRUE;
    public static final Func1<Object, Boolean> FUNC1_ALWAYS_TRUE = ALWAYS_TRUE;

    private static final class Always<T> implements Func0<T>, Func1<Object, T> {
        private final T value;

        private Always(T value) {
            this.value = value;
        }

        @Override
        public T call() {
            return value;
        }

        @Override
        public T call(Object o) {
            return value;
        }
    }

    private Functions() {
        throw new AssertionError("No instances");
    }
}
