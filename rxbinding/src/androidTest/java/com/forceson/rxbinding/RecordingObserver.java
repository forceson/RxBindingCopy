package com.forceson.rxbinding;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import rx.Observer;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-11.
 */
public class RecordingObserver<T> implements Observer<T> {
    private static final String TAG = "RecordingObserver";

    private final BlockingDeque<Object> events = new LinkedBlockingDeque<>();

    @Override
    public void onCompleted() {
        Log.v(TAG, "onComplete");
        events.addLast(new OnComplete());
    }

    @Override
    public void onError(Throwable e) {
        Log.v(TAG, "onError", e);
        events.addLast(new OnError(e));
    }

    @Override
    public void onNext(T t) {
        Log.v(TAG, "onNext" + t);
        events.addLast(new OnNext(t));
    }

    private <E> E takeEvent(Class<E> wanted) {
        Object event;
        try {
            event = events.pollFirst(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (event == null) {
            throw new NoSuchElementException(
                    "No event found while waiting for " + wanted.getSimpleName());
        }
        assertThat(event).isInstanceOf(wanted);
        return wanted.cast(event);
    }

    public T takeNext() {
        OnNext event = takeEvent(OnNext.class);
        return event.value;
    }

    public Throwable takeError() {
        return takeEvent(OnError.class).throwable;
    }

    public void assertOnCompleted() {
        takeEvent(OnComplete.class);
    }

    public void assertNoMoreEvents() {
        try {
            Object event = takeEvent(Object.class);
            throw new IllegalStateException("Expected no more events but got " + event);
        } catch (NoSuchElementException ignored) {
        }
    }

    private class OnNext {
        final T value;

        private OnNext(T value) {
            this.value = value;
        }

        @NonNull
        @Override
        public String toString() {
            return "OnNext[" + value + "]";
        }
    }

    private final class OnComplete {
        @NonNull
        @Override
        public String toString() {
            return "OnComplete";
        }
    }

    private class OnError {
        private final Throwable throwable;

        private OnError(Throwable throwable) {
            this.throwable = throwable;
        }

        @NonNull
        @Override
        public String toString() {
            return "OnError[" + throwable + "]";
        }
    }
}
