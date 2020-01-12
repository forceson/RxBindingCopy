package com.forceson.rxbinding.schedulers;

import android.os.Handler;
import android.os.Looper;

import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Scheduler;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-09.
 */
public final class HandlerSchedulers {
    private static final Scheduler MAIN_THREAD_SCHEDULER =
            new HandlerScheduler(new Handler(Looper.getMainLooper()));

    public static Scheduler from(final Handler handler) {
        checkNotNull(handler, "handler == null");
        return new HandlerScheduler(handler);
    }

    public static Scheduler mainThread() {
        Scheduler scheduler =
                RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
        return scheduler != null ? scheduler : MAIN_THREAD_SCHEDULER;
    }

    private HandlerSchedulers() {
        throw new AssertionError("No instances.");
    }
}
