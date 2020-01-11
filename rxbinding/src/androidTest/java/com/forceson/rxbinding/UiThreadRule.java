package com.forceson.rxbinding;

import android.os.Handler;
import android.os.Looper;

import androidx.test.annotation.UiThreadTest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by son on 2020-01-10.
 */
public final class UiThreadRule implements TestRule {

    public static UiThreadRule create() {
        return new UiThreadRule(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    public static UiThreadRule createWithTimeout(int amount, TimeUnit unit) {
        return new UiThreadRule(amount, unit);
    }

    private final Handler mainThread = new Handler(Looper.getMainLooper());
    private final long timeoutAmount;
    private final TimeUnit timeoutUnit;

    public UiThreadRule(long amount, TimeUnit unit) {
        this.timeoutAmount = amount;
        this.timeoutUnit = unit;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        if (description.getAnnotation(UiThreadTest.class) == null) {
            return base;
        }
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final CountDownLatch latch = new CountDownLatch(1);
                final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            base.evaluate();
                        } catch (Throwable throwable) {
                            throwableRef.set(throwable);
                        } finally {
                            latch.countDown();
                        }
                    }
                });
                if (!latch.await(timeoutAmount, timeoutUnit)) {
                    throw new TimeoutException(
                            "Test took longer than " + timeoutAmount + " " + timeoutUnit.name().toLowerCase(Locale.US)
                    );
                }
                Throwable thrown = throwableRef.get();
                if (thrown != null) {
                    throw thrown;
                }
            }
        };
    }
}
