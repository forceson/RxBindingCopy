package com.forceson.rxbinding;

import android.os.Handler;

import com.forceson.rxbinding.schedulers.HandlerSchedulers;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;

import static com.google.common.truth.Truth.assertThat;
import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by son on 2020-01-10.
 */
public final class HandlerSchedulersTest {
    @Test
    public void nullThrows() {
        try {
            HandlerSchedulers.from(null);
            fail();
        } catch (NullPointerException e) {
            assertThat(e).hasMessage("handler == null");
        }
    }

    @Test
    public void schedulePostsImmediately() {
        Handler handler = mock(Handler.class);
        Action0 action = mock(Action0.class);

        Scheduler scheduler = HandlerSchedulers.from(handler);
        Scheduler.Worker inner = scheduler.createWorker();
        inner.schedule(action);

        ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(handler).postDelayed(runnableCaptor.capture(), eq(0L));

        runnableCaptor.getValue().run();
        verify(action).call();
    }

    @Test
    public void scheduleDelayedPostsDelayed() {
        Handler handler = mock(Handler.class);
        Action0 action = mock(Action0.class);

        Scheduler scheduler = HandlerSchedulers.from(handler);
        Scheduler.Worker inner = scheduler.createWorker();
        inner.schedule(action, 1L, TimeUnit.SECONDS);

        ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(handler).postDelayed(runnableCaptor.capture(), eq(1000L));

        runnableCaptor.getValue().run();
        verify(action).call();
    }

    @Test
    public void unsubscribeRemoveCallbacks() {
        Handler handler = mock(Handler.class);
        Action0 action = mock(Action0.class);

        Scheduler scheduler = HandlerSchedulers.from(handler);
        Scheduler.Worker inner = scheduler.createWorker();
        Subscription subscription = inner.schedule(action);

        verify(handler).postDelayed(any(Runnable.class), eq(0L));

        subscription.unsubscribe();

        ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(handler).removeCallbacks(runnableCaptor.capture());

        runnableCaptor.getValue().run();
        verify(action).call();
    }
}
