package com.forceson.rxbinding;

import com.forceson.rxbinding.plugins.RxAndroidClockHook;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by son on 2020-01-11.
 */
public class FakeClock extends RxAndroidClockHook {
    private long timeMs;

    @Override
    public long uptimeMillis() {
        return timeMs;
    }

    public void advance(long amount, TimeUnit unit) {
        if (unit == null) {
            throw new NullPointerException("unit == null");
        }
        if (amount < 0) {
            throw new IllegalStateException(
                    "Can only advance forward " + amount + " " + unit.name().toLowerCase(Locale.US));
        }
        timeMs += unit.toMillis(amount);
    }
}
