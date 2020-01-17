package com.forceson.rxbinding;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.test.InstrumentationRegistry;
import androidx.test.annotation.UiThreadTest;
import androidx.test.runner.AndroidJUnit4;

import com.forceson.rxbinding.widget.CompoundButtonCheckedChangeEvent;
import com.forceson.rxbinding.widget.RxCompoundButton;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscription;
import rx.functions.Action1;

import static com.google.common.truth.Truth.assertThat;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by son on 2020-01-11.
 */
@RunWith(AndroidJUnit4.class)
public final class RxCompoundButtonTest {
    @Rule
    public final UiThreadRule uiThread = UiThreadRule.createWithTimeout(10, SECONDS);

    private final Context context = InstrumentationRegistry.getContext();
    private final CompoundButton view = new ToggleButton(context);

    @Test
    @UiThreadTest
    public void checkedChanges() {
        view.setChecked(false);

        RecordingObserver<Boolean> o = new RecordingObserver<>();
        Subscription subscription = RxCompoundButton.checkedChanges(view).subscribe(o);
        assertThat(o.takeNext()).isFalse();

        view.setChecked(true);
        assertThat(o.takeNext()).isTrue();
        view.setChecked(false);
        assertThat(o.takeNext()).isFalse();

        subscription.unsubscribe();

        view.setChecked(true);
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void checkedChangeEvents() {
        view.setChecked(false);

        RecordingObserver<CompoundButtonCheckedChangeEvent> o = new RecordingObserver<>();
        Subscription subscription = RxCompoundButton.checkedChangeEvents(view).subscribe(o);
        CompoundButtonCheckedChangeEvent event0 = o.takeNext();
        assertThat(event0.view()).isSameAs(view);
        assertThat(event0.isChecked()).isFalse();

        view.setChecked(true);
        CompoundButtonCheckedChangeEvent event1 = o.takeNext();
        assertThat(event1.view()).isSameAs(view);
        assertThat(event1.isChecked()).isTrue();

        view.setChecked(false);
        CompoundButtonCheckedChangeEvent event2 = o.takeNext();
        assertThat(event2.view()).isSameAs(view);
        assertThat(event2.isChecked()).isFalse();

        subscription.unsubscribe();

        view.setChecked(true);
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setChecked() {
        view.setChecked(false);
        Action1<? super Boolean> toggle = RxCompoundButton.setChecked(view);

        toggle.call(true);
        assertThat(view.isChecked()).isTrue();

        toggle.call(false);
        assertThat(view.isChecked()).isFalse();
    }

    @Test
    @UiThreadTest
    public void toggle() {
        view.setChecked(false);
        Action1<? super Object> toggle = RxCompoundButton.toggle(view);

        toggle.call(null);
        assertThat(view.isChecked()).isTrue();

        toggle.call(null);
        assertThat(view.isChecked()).isFalse();
    }
}
