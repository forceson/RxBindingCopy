package com.forceson.rxbinding.internal;

import android.os.Looper;

/**
 * Created by son on 2020-01-11.
 */
public final class Preconditions {
    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    public static void checkState(boolean assertion, String message) {
        if (!assertion) {
            throw new IllegalStateException(message);
        }
    }

    public static void checkArgument(boolean assertion, String message) {
        if (!assertion) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
