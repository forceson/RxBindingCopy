package com.forceson.rxbinding;

import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by son on 2020-01-17.
 */
public final class PluginResetRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins plugins = RxAndroidPlugins.getInstance();

                plugins.reset();
                try {
                    base.evaluate();
                } finally {
                    plugins.reset();
                }
            }
        };
    }
}
