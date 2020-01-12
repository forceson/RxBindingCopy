package com.forceson.rxbinding.plugins;

import android.util.Log;

/**
 * Created by son on 2020-01-13.
 */
public class RxAndroidLogHook {
    private static final RxAndroidLogHook DEFAULT_INSTANCE = new RxAndroidLogHook();

    public static RxAndroidLogHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public int log(int priority, String tag, String msg) {
        return Log.println(priority, tag, msg);
    }

    public int log(int priority, String tag, String msg, Throwable throwable) {
        return Log.println(priority, tag, msg + '\n' + Log.getStackTraceString(throwable));
    }
}
