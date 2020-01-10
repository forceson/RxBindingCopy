package com.forceson.rxbinding;

import com.forceson.rxbinding.plugins.RxAndroidPlugins;
import com.forceson.rxbinding.plugins.RxAndroidSchedulerHook;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static junit.framework.TestCase.fail;

/**
 * Created by son on 2020-01-10.
 */
public class RxAndroidPluginsTest {
    @Test
    public void registeredSchedulersHookIsUsed() {
        RxAndroidPlugins plugins = new RxAndroidPlugins();
        RxAndroidSchedulerHook hook = new RxAndroidSchedulerHook();
        plugins.registerSchedulersHook(hook);
        assertThat(plugins.getSchedulersHook()).isSameAs(hook);
    }

    @Test
    public void registerSchedulersHookViaSystemProperty() {
        // TODO this test!
    }

    @Test
    public void registerSchedulersHookTwiceFails() {
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
}
