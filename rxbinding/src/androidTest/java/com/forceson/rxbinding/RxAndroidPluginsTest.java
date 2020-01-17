package com.forceson.rxbinding;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.plugins.RxAndroidLogHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;
import com.forceson.rxbinding.plugins.RxAndroidSchedulerHook;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by son on 2020-01-17.
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public final class RxAndroidPluginsTest {
    @Test
    public void schedulersHookIsUsed() {
        RxAndroidPlugins plugins = new RxAndroidPlugins();
        RxAndroidSchedulerHook schedulerHook = new RxAndroidSchedulerHook();
        plugins.registerSchedulersHook(schedulerHook);
        assertThat(plugins.getSchedulersHook()).isSameAs(schedulerHook);
    }

    public static class MyRxAndroidSchedulersHook extends RxAndroidSchedulerHook {
    }

    @Test
    public void schedulersHookViaSystemPropertyIsUsed() {
        String name = "rxbinding.plugins.RxAndroidSchedulerHook.implementation";
        try {
            System.setProperty(name, MyRxAndroidSchedulersHook.class.getName());
            RxAndroidPlugins plugins = new RxAndroidPlugins();

            assertThat(plugins.getSchedulersHook()).isInstanceOf(MyRxAndroidSchedulersHook.class);
        } finally {
            System.clearProperty(name);
        }
    }

    @Test
    public void schedulersHookTwiceFails() {
        RxAndroidPlugins plugins = new RxAndroidPlugins();
        RxAndroidSchedulerHook hook = new RxAndroidSchedulerHook();
        plugins.registerSchedulersHook(hook);
        try {
            plugins.registerSchedulersHook(hook);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).startsWith("Another strategy was already registered:");
        }
    }

    @Test
    public void logHookIsUsed() {
        RxAndroidPlugins plugins = new RxAndroidPlugins();
        RxAndroidLogHook logHook = new RxAndroidLogHook();
        plugins.registerLogHook(logHook);
        assertThat(plugins.getLogHook()).isSameAs(logHook);
    }

    public static class MyRxAndroidLogHook extends RxAndroidLogHook {
    }

    @Test
    public void logHookViaSystemPropertyIsUsed() {
        String name = "rxbinding.plugins.RxAndroidLogHook.implementation";
        try {
            System.setProperty(name, MyRxAndroidLogHook.class.getName());
            RxAndroidPlugins plugins = new RxAndroidPlugins();
            assertThat(plugins.getLogHook()).isInstanceOf(MyRxAndroidLogHook.class);
        } finally {
            System.clearProperty(name);
        }
    }

    @Test
    public void logHookTwiceFails() {
        RxAndroidPlugins plugins = new RxAndroidPlugins();
        RxAndroidLogHook hook = new RxAndroidLogHook();
        plugins.registerLogHook(hook);
        try {
            plugins.registerLogHook(hook);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).startsWith("Another strategy was already registered:");
        }
    }
}
