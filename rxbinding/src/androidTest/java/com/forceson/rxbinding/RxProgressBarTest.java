package com.forceson.rxbinding;

import android.content.Context;
import android.widget.ProgressBar;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.widget.RxProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.functions.Action1;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-25.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public final class RxProgressBarTest {
    private final Context context = ApplicationProvider.getApplicationContext();
    private final ProgressBar view = new ProgressBar(context, null, 0);

    @Test
    @UiThreadTest
    public void incrementProgressBy() {
        Action1<? super Integer> action = RxProgressBar.incrementProgressBy(view);
        assertThat(view.getProgress()).isEqualTo(0);
        action.call(10);
        assertThat(view.getProgress()).isEqualTo(10);
        action.call(20);
        assertThat(view.getProgress()).isEqualTo(30);
        action.call(30);
        assertThat(view.getProgress()).isEqualTo(60);
        action.call(40);
        assertThat(view.getProgress()).isEqualTo(100);
    }

    @Test
    @UiThreadTest
    public void incrementSecondaryProgressBy() {
        Action1<? super Integer> action = RxProgressBar.incrementSecondaryProgressBy(view);
        assertThat(view.getSecondaryProgress()).isEqualTo(0);
        action.call(10);
        assertThat(view.getSecondaryProgress()).isEqualTo(10);
        action.call(20);
        assertThat(view.getSecondaryProgress()).isEqualTo(30);
        action.call(30);
        assertThat(view.getSecondaryProgress()).isEqualTo(60);
        action.call(40);
        assertThat(view.getSecondaryProgress()).isEqualTo(100);
    }

    @Test
    @UiThreadTest
    public void setIndeterminate() {
        Action1<? super Boolean> action = RxProgressBar.setIndeterminate(view);
        action.call(true);
        assertThat(view.isIndeterminate()).isTrue();
        action.call(false);
        assertThat(view.isIndeterminate()).isFalse();
    }

    @Test
    @UiThreadTest
    public void setMax() {
        Action1<? super Integer> action = RxProgressBar.setMax(view);
        action.call(100);
        assertThat(view.getMax()).isEqualTo(100);
        action.call(1000);
        assertThat(view.getMax()).isEqualTo(1000);
    }

    @Test
    @UiThreadTest
    public void setProgress() {
        Action1<? super Integer> action = RxProgressBar.setProgress(view);
        assertThat(view.getProgress()).isEqualTo(0);
        action.call(50);
        assertThat(view.getProgress()).isEqualTo(50);
        action.call(100);
        assertThat(view.getProgress()).isEqualTo(100);
    }

    @Test
    @UiThreadTest
    public void setSecondaryProgress() {
        Action1<? super Integer> action = RxProgressBar.setSecondaryProgress(view);
        assertThat(view.getSecondaryProgress()).isEqualTo(0);
        action.call(50);
        assertThat(view.getSecondaryProgress()).isEqualTo(50);
        action.call(100);
        assertThat(view.getSecondaryProgress()).isEqualTo(100);
    }


}
