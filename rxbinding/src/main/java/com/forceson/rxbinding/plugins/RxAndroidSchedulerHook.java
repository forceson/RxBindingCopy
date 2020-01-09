package com.forceson.rxbinding.plugins;

import rx.Scheduler;
import rx.functions.Action0;

/**
 * Created by son on 2020-01-09.
 */
public final class RxAndroidSchedulerHook {
    private static final RxAndroidSchedulerHook DEFAULT_INSTANCE = new RxAndroidSchedulerHook();

    public static RxAndroidSchedulerHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Scheduler getMainThreadScheduler() {
        return null;
    }

    public Action0 onSchedule(Action0 action) {
        return action;
    }
}
