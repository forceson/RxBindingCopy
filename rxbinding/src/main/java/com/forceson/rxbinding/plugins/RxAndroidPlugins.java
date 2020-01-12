package com.forceson.rxbinding.plugins;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by son on 2020-01-09.
 */
public final class RxAndroidPlugins {
    private static final RxAndroidPlugins INSTANCE = new RxAndroidPlugins();

    public static RxAndroidPlugins getInstance() {
        return INSTANCE;
    }

    private final AtomicReference<RxAndroidSchedulerHook> schedulersHook = new AtomicReference<>();
    private final AtomicReference<RxAndroidClockHook> clockHook = new AtomicReference<>();
    private final AtomicReference<RxAndroidLogHook> logHook = new AtomicReference<>();

    public RxAndroidPlugins() {

    }

    public void reset() {
        schedulersHook.set(null);
        clockHook.set(null);
        logHook.set(null);
    }

    public RxAndroidSchedulerHook getSchedulersHook() {
        if (schedulersHook.get() == null) {
            RxAndroidSchedulerHook impl =
                    getPluginImplementationViaProperty(RxAndroidSchedulerHook.class);
            if (impl == null) {
                schedulersHook.compareAndSet(null, RxAndroidSchedulerHook.getDefaultInstance());
            } else {
                schedulersHook.compareAndSet(null, impl);
            }
        }
        return schedulersHook.get();
    }

    public RxAndroidClockHook getClockHook() {
        if (clockHook.get() == null) {
            RxAndroidClockHook impl =
                    getPluginImplementationViaProperty(RxAndroidClockHook.class);
            if (impl == null) {
                clockHook.compareAndSet(null, RxAndroidClockHook.getDefaultInstance());
            } else {
                clockHook.compareAndSet(null, impl);
            }
        }
        return clockHook.get();
    }

    public RxAndroidLogHook getLogHook() {
        if (logHook.get() == null) {
            RxAndroidLogHook impl =
                    getPluginImplementationViaProperty(RxAndroidLogHook.class);
            if (impl == null) {
                logHook.compareAndSet(null, RxAndroidLogHook.getDefaultInstance());
            } else {
                logHook.compareAndSet(null, impl);
            }
        }
        return logHook.get();
    }

    @SuppressWarnings("unchecked") // Burden of correctness is on the property setter.
    private static <T> T getPluginImplementationViaProperty(Class<T> pluginClass) {
        String classSimpleName = pluginClass.getSimpleName();

        String implementingClass =
                System.getProperty("rxbinding.plugin." + classSimpleName + ".implementation");
        if (implementingClass != null) {
            try {
                Class<?> cls = Class.forName(implementingClass);
                return (T) cls.newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(
                        classSimpleName + " implementation class not found: " + implementingClass, e);
            } catch (InstantiationException e) {
                throw new RuntimeException(
                        classSimpleName + " implementation not able to be instantiated: " + implementingClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(
                        classSimpleName + " implementation not able to be accessed: " + implementingClass, e);
            }
        } else {
            return null;
        }
    }

    public void registerSchedulersHook(RxAndroidSchedulerHook impl) {
        if (!schedulersHook.compareAndSet(null, impl)) {
            throw new IllegalStateException(
                    "Another strategy was already registered: " + schedulersHook.get());
        }
    }

    public void registerClockHook(RxAndroidClockHook impl) {
        if (!clockHook.compareAndSet(null, impl)) {
            throw new IllegalStateException(
                    "Another strategy was already registered: " + clockHook.get());
        }
    }

    public void registerLogHook(RxAndroidLogHook impl) {
        if (!logHook.compareAndSet(null, impl)) {
            throw new IllegalStateException(
                    "Another strategy was already registered: " + clockHook.get());
        }
    }
}
