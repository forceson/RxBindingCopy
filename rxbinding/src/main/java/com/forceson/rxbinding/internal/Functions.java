package com.forceson.rxbinding.internal;

import java.util.concurrent.Callable;

import io.reactivex.functions.Function;

/**
 * Created by son on 2020-01-10.
 */
public class Functions {
    public static Always<Boolean> ALWAYS_TRUE = new Always<>(true);
    public static final Callable<Boolean> FUNC0_ALWAYS_TRUE = ALWAYS_TRUE;
    public static final Function<Object, Boolean> FUNC1_ALWAYS_TRUE = ALWAYS_TRUE;

    private static final class Always<T> implements Function<Object, T>, Callable<T> {
        private final T value;

        private Always(T value) {
            this.value = value;
        }

        @Override
        public T call() {
            return value;
        }

        @Override
        public T apply(Object o) throws Exception {
            return value;
        }
    }

    private Functions() {
        throw new AssertionError("No instances");
    }
}
