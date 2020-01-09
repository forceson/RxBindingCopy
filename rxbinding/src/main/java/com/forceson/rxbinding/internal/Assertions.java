package com.forceson.rxbinding.internal;

import android.os.Looper;

/**
 * Created by son on 2020-01-09.
 */
public class Assertions {
    public static void assertUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }

    private Assertions() {
        throw new AssertionError("No instances.");
    }
}
