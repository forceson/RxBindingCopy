package com.forceson.rxbinding.util;

import android.util.Log;

import com.forceson.rxbinding.plugins.RxAndroidLogHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.functions.Action1;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-13.
 */
public final class RxLog {
    public static Action1<? super Object> v(String tag) {
        return log(Log.VERBOSE, tag);
    }

    public static Action1<? super Object> d(String tag) {
        return log(Log.DEBUG, tag);
    }

    public static Action1<? super Object> i(String tag) {
        return log(Log.INFO, tag);
    }

    public static Action1<? super Object> w(String tag) {
        return log(Log.WARN, tag);
    }

    public static Action1<? super Object> e(String tag) {
        return log(Log.ERROR, tag);
    }

    private static Action1<? super Object> log(final int priority, final String tag) {
        checkNotNull(tag, "tag == null");

        final RxAndroidLogHook logHook = RxAndroidPlugins.getInstance().getLogHook();
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                logHook.log(priority, tag, String.valueOf(o));
            }
        };
    }

    public static Action1<? super Throwable> error(String tag) {
        return error(tag, "");
    }

    public static Action1<? super Throwable> error(final String tag, final String message) {
        checkNotNull(tag, "tag == null");
        checkNotNull(message, "message == null");

        final RxAndroidLogHook logHook = RxAndroidPlugins.getInstance().getLogHook();
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                logHook.log(Log.ERROR, tag, message, throwable);
            }
        };
    }

    private RxLog() {
        throw new AssertionError("No instances.");
    }
}
