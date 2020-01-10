package com.forceson.rxbinding.plugins;

import android.os.SystemClock;

/**
 * Created by son on 2020-01-09.
 */
public class RxAndroidClockHook {
    private static final RxAndroidClockHook DEFAULT_INSTANCE = new RxAndroidClockHook();

    public static RxAndroidClockHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /**
     * Invoked for any view events to record the time of the event. The default delegates to
     * {@link SystemClock#uptimeMillis()}.
     * @return milliseconds of non-sleep uptime since boot.
     */
    public long uptimeMillis() {
        return SystemClock.uptimeMillis();
    }
}
