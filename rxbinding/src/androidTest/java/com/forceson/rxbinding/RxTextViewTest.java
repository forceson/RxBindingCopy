package com.forceson.rxbinding;

import android.content.Context;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;
import androidx.test.annotation.UiThreadTest;
import androidx.test.runner.AndroidJUnit4;

import com.forceson.rxbinding.widget.RxTextView;
import com.forceson.rxbinding.widget.TextViewTextChangeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscription;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-11.
 */
@RunWith(AndroidJUnit4.class)
public final class RxTextViewTest {
    private final Context context = InstrumentationRegistry.getContext();
    private final TextView view = new TextView(context);

    @Test
    @UiThreadTest
    public void textChanges() {
        view.setText("Initial");

        RecordingObserver<CharSequence> o = new RecordingObserver<>();
        Subscription subscription = RxTextView.textChanges(view).subscribe(o);
        assertThat(o.takeNext().toString()).isEqualTo("Initial");

        view.setText("H");
        assertThat(o.takeNext().toString()).isEqualTo("H");
        view.setText("He");
        assertThat(o.takeNext().toString()).isEqualTo("He");
        view.setText(null);
        assertThat(o.takeNext().toString()).isEqualTo("");

        subscription.unsubscribe();

        view.setText("Silent");
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void textChangeEvents() {
        view.setText("Initial");

        RecordingObserver<TextViewTextChangeEvent> o = new RecordingObserver<>();
        Subscription subscription = RxTextView.textChangeEvents(view).subscribe(o);
        TextViewTextChangeEvent event0 = o.takeNext();
        assertThat(event0.view()).isSameAs(view);
        assertThat(event0.text().toString()).isEqualTo("Initial");
        assertThat(event0.start()).isEqualTo(0);
        assertThat(event0.before()).isEqualTo(0);
        assertThat(event0.count()).isEqualTo(0);

        view.setText("H");
        TextViewTextChangeEvent event1 = o.takeNext();
        assertThat(event1.view()).isSameAs(view);
        assertThat(event1.text().toString()).isEqualTo("H");
        assertThat(event1.start()).isEqualTo(0);
        assertThat(event1.before()).isEqualTo(7);
        assertThat(event1.count()).isEqualTo(1);

        view.setText("He");
        TextViewTextChangeEvent event2 = o.takeNext();
        assertThat(event2.view()).isSameAs(view);
        assertThat(event2.text().toString()).isEqualTo("He");
        assertThat(event2.start()).isEqualTo(0);
        assertThat(event2.before()).isEqualTo(1);
        assertThat(event2.count()).isEqualTo(2);

        subscription.unsubscribe();

        view.setText("Silent");
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setText() {
        RxTextView.setText(view).call("Hey");
        assertThat(view.getText().toString()).isEqualTo("Hey");
    }

    @Test
    @UiThreadTest
    public void setTextRes() {
        RxTextView.setTextRes(view).call(R.string.hey);
        assertThat(view.getText().toString()).isEqualTo("Hey");
    }
}
