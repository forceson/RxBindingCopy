package com.forceson.rxbinding;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.widget.RxCompoundButton;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.functions.Consumer;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-11.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public final class RxCompoundButtonTest {
    private final Context context = ApplicationProvider.getApplicationContext();
    private final CompoundButton view = new ToggleButton(context);

    @Test
    @UiThreadTest
    public void checkedChanges() {
        view.setChecked(false);

        RecordingObserver<Boolean> o = new RecordingObserver<>();
        RxCompoundButton.checkedChanges(view).subscribe(o);
        assertThat(o.takeNext()).isFalse();

        view.setChecked(true);
        assertThat(o.takeNext()).isTrue();
        view.setChecked(false);
        assertThat(o.takeNext()).isFalse();

        o.dispose();

        view.setChecked(true);
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setChecked() {
        view.setChecked(false);
        Consumer<? super Boolean> toggle = RxCompoundButton.setChecked(view);

        try {
            toggle.accept(true);
            assertThat(view.isChecked()).isTrue();

            toggle.accept(false);
            assertThat(view.isChecked()).isFalse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void toggle() {
        view.setChecked(false);
        Consumer<? super Object> toggle = RxCompoundButton.toggle(view);

        try {
            toggle.accept(null);
            assertThat(view.isChecked()).isTrue();

            toggle.accept(null);
            assertThat(view.isChecked()).isFalse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
