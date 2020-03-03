package com.forceson.rxbinding;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.view.RxView;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static junit.framework.TestCase.fail;

/**
 * Created by son on 2020-01-10.
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public final class RxViewTest {
    private final Context context = ApplicationProvider.getApplicationContext();
    private final View view = new View(context);

    @Test
    @UiThreadTest
    public void clicks() {
        RecordingObserver<Object> o = new RecordingObserver<>();
        Subscription subscription = RxView.clicks(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performClick();
        assertThat(o.takeNext()).isNotNull();

        view.performClick();
        assertThat(o.takeNext()).isNotNull();

        subscription.unsubscribe();

        view.performClick();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void clickEvents() {
        RecordingObserver<ViewClickEvent> o = new RecordingObserver<>();
        Subscription subscription = RxView.clickEvents(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performClick();
        assertThat(o.takeNext()).isEqualTo(ViewClickEvent.create(view));

        view.performClick();
        assertThat(o.takeNext()).isEqualTo(ViewClickEvent.create(view));

        subscription.unsubscribe();

        view.performClick();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void drags() {
        //RecordingObserver<ViewClickEvent> o = new RecordingObserver<>();
        //Subscription subscription = RxView.clickEvents(view).subscribe(o);
        //o.assertNoMoreEvents(); // No initial value.
        //
        //clock.advance(1, SECONDS);
        //view.performClick();
        //assertThat(o.takeNext()).isEqualTo(ViewClickEvent.create(view, 1000));
        //
        //clock.advance(1, SECONDS);
        //view.performClick();
        //assertThat(o.takeNext()).isEqualTo(ViewClickEvent.create(view, 2000));
        //
        //subscription.unsubscribe();
        //
        //clock.advance(1, SECONDS);
        //view.performClick();
        //o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void focusChanges() {
        // We need a parent which can take focus from our view when it attempts to clear.
        LinearLayout parent = new LinearLayout(context);
        parent.setFocusable(true);
        parent.addView(view);

        view.setFocusable(true);

        RecordingObserver<Boolean> o = new RecordingObserver<>();
        Subscription subscription = RxView.focusChanges(view).subscribe(o);
        assertThat(o.takeNext()).isFalse();

        view.requestFocus();
        assertThat(o.takeNext()).isTrue();

        view.clearFocus();
        assertThat(o.takeNext()).isFalse();

        subscription.unsubscribe();

        view.requestFocus();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void focusChangeEvents() {
        // We need a parent which can take focus from our view when it attempts to clear.
        LinearLayout parent = new LinearLayout(context);
        parent.setFocusable(true);
        parent.addView(view);

        view.setFocusable(true);

        RecordingObserver<ViewFocusChangeEvent> o = new RecordingObserver<>();
        Subscription subscription = RxView.focusChangeEvents(view).subscribe(o);
        assertThat(o.takeNext()).isEqualTo(ViewFocusChangeEvent.create(view, false));

        view.requestFocus();
        assertThat(o.takeNext()).isEqualTo(ViewFocusChangeEvent.create(view, true));

        view.clearFocus();
        assertThat(o.takeNext()).isEqualTo(ViewFocusChangeEvent.create(view, false));

        subscription.unsubscribe();

        view.requestFocus();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void longClicks() {
        // We need a parent because long presses delegate to the parent.
        LinearLayout parent = new LinearLayout(context) {
            @Override
            public boolean showContextMenuForChild(View originalView) {
                return true;
            }
        };
        parent.addView(view);

        RecordingObserver<Object> o = new RecordingObserver<>();
        Subscription subscription = RxView.longClicks(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performLongClick();
        assertThat(o.takeNext()).isNotNull();

        view.performLongClick();
        assertThat(o.takeNext()).isNotNull();

        subscription.unsubscribe();

        view.performLongClick();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void longClickEvents() {
        // We need a parent because long presses delegate to the parent.
        LinearLayout parent = new LinearLayout(context) {
            @Override
            public boolean showContextMenuForChild(View originalView) {
                return true;
            }
        };
        parent.addView(view);

        RecordingObserver<ViewLongClickEvent> o = new RecordingObserver<>();
        Subscription subscription = RxView.longClickEvents(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performLongClick();
        assertThat(o.takeNext()).isEqualTo(ViewLongClickEvent.create(view));

        view.performLongClick();
        assertThat(o.takeNext()).isEqualTo(ViewLongClickEvent.create(view));

        subscription.unsubscribe();

        view.performLongClick();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setActivated() {
        view.setActivated(true);
        Action1<? super Boolean> action = RxView.setActivated(view);
        action.call(false);
        assertThat(view.isActivated()).isFalse();
        action.call(true);
        assertThat(view.isActivated()).isTrue();
    }

    @Test
    @UiThreadTest
    public void setEnabled() {
        view.setEnabled(true);
        Action1<? super Boolean> action = RxView.setEnabled(view);
        action.call(false);
        assertThat(view.isEnabled()).isFalse();
        action.call(true);
        assertThat(view.isEnabled()).isTrue();
    }

    @Test
    @UiThreadTest
    public void setPressed() {
        view.setPressed(true);
        Action1<? super Boolean> action = RxView.setPressed(view);
        action.call(false);
        assertThat(view.isPressed()).isFalse();
        action.call(true);
        assertThat(view.isPressed()).isTrue();
    }

    @Test
    @UiThreadTest
    public void setSelected() {
        view.setSelected(true);
        Action1<? super Boolean> action = RxView.setSelected(view);
        action.call(false);
        assertThat(view.isSelected()).isFalse();
        action.call(true);
        assertThat(view.isSelected()).isTrue();
    }

    @Test
    @UiThreadTest
    public void setVisibility() {
        view.setVisibility(View.VISIBLE);
        Action1<? super Boolean> action = RxView.setVisibility(view);
        action.call(false);
        assertThat(view.getVisibility()).isEqualTo(View.GONE);
        action.call(true);
        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    @UiThreadTest
    public void setVisibilityCustomFalse() {
        view.setVisibility(View.VISIBLE);
        Action1<? super Boolean> action = RxView.setVisibility(view, View.INVISIBLE);
        action.call(false);
        assertThat(view.getVisibility()).isEqualTo(View.INVISIBLE);
        action.call(true);
        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    @UiThreadTest
    public void setVisibilityCustomFalseToVisibleThrows() {
        try {
            RxView.setVisibility(view, View.VISIBLE);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("Setting visibility to VISIBLE when false would have no effect.");
        }
    }
}
