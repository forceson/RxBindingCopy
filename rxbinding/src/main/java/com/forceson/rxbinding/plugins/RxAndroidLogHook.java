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

    public void log(int priority, String tag, String msg, Throwable throwable) {
        if (msg == null || msg.isEmpty()) {
            if (throwable == null) {
                return;
            }
            msg = Log.getStackTraceString(throwable);
        } else if (throwable != null) {
            msg += '\n' + Log.getStackTraceString(throwable);
        }
        Log.println(priority, tag, msg);
    }
}
