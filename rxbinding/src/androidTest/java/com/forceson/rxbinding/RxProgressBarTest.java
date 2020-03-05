package com.forceson.rxbinding;

import android.content.Context;
import android.widget.ProgressBar;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.widget.RxProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.functions.Consumer;

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
        Consumer<? super Integer> action = RxProgressBar.incrementProgressBy(view);
        assertThat(view.getProgress()).isEqualTo(0);
        try {
            action.accept(10);
            assertThat(view.getProgress()).isEqualTo(10);
            action.accept(20);
            assertThat(view.getProgress()).isEqualTo(30);
            action.accept(30);
            assertThat(view.getProgress()).isEqualTo(60);
            action.accept(40);
            assertThat(view.getProgress()).isEqualTo(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void incrementSecondaryProgressBy() {
        Consumer<? super Integer> action = RxProgressBar.incrementSecondaryProgressBy(view);
        assertThat(view.getSecondaryProgress()).isEqualTo(0);
        try {
            action.accept(10);
            assertThat(view.getSecondaryProgress()).isEqualTo(10);
            action.accept(20);
            assertThat(view.getSecondaryProgress()).isEqualTo(30);
            action.accept(30);
            assertThat(view.getSecondaryProgress()).isEqualTo(60);
            action.accept(40);
            assertThat(view.getSecondaryProgress()).isEqualTo(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setIndeterminate() {
        Consumer<? super Boolean> action = RxProgressBar.setIndeterminate(view);
        try {
            action.accept(true);
            assertThat(view.isIndeterminate()).isTrue();
            action.accept(false);
            assertThat(view.isIndeterminate()).isFalse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setMax() {
        Consumer<? super Integer> action = RxProgressBar.setMax(view);
        try {
            action.accept(100);
            assertThat(view.getMax()).isEqualTo(100);
            action.accept(1000);
            assertThat(view.getMax()).isEqualTo(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setProgress() {
        Consumer<? super Integer> action = RxProgressBar.setProgress(view);
        assertThat(view.getProgress()).isEqualTo(0);
        try {
            action.accept(50);
            assertThat(view.getProgress()).isEqualTo(50);
            action.accept(100);
            assertThat(view.getProgress()).isEqualTo(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setSecondaryProgress() {
        Consumer<? super Integer> action = RxProgressBar.setSecondaryProgress(view);
        assertThat(view.getSecondaryProgress()).isEqualTo(0);
        try {
            action.accept(50);
            assertThat(view.getSecondaryProgress()).isEqualTo(50);
            action.accept(100);
            assertThat(view.getSecondaryProgress()).isEqualTo(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
