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

import io.reactivex.functions.Consumer;

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
        RxView.clicks(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performClick();
        assertThat(o.takeNext()).isNotNull();

        view.performClick();
        assertThat(o.takeNext()).isNotNull();

        o.dispose();

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
        RxView.focusChanges(view).subscribe(o);
        assertThat(o.takeNext()).isFalse();

        o.dispose();

//        view.requestFocus();
//        assertThat(o.takeNext()).isTrue();
//
//        view.clearFocus();
//        assertThat(o.takeNext()).isFalse();
//
//        o.dispose();

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
        RxView.longClicks(view).subscribe(o);
        o.assertNoMoreEvents(); // No initial value.

        view.performLongClick();
        assertThat(o.takeNext()).isNotNull();

        view.performLongClick();
        assertThat(o.takeNext()).isNotNull();

        o.dispose();

        view.performLongClick();
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setActivated() {
        view.setActivated(true);
        Consumer<? super Boolean> action = RxView.setActivated(view);
        try {
            action.accept(false);
            assertThat(view.isActivated()).isFalse();
            action.accept(true);
            assertThat(view.isActivated()).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setEnabled() {
        view.setEnabled(true);
        Consumer<? super Boolean> action = RxView.setEnabled(view);
        try {
            action.accept(false);
            assertThat(view.isEnabled()).isFalse();
            action.accept(true);
            assertThat(view.isEnabled()).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setPressed() {
        view.setPressed(true);
        Consumer<? super Boolean> action = RxView.setPressed(view);
        try {
            action.accept(false);
            assertThat(view.isPressed()).isFalse();
            action.accept(true);
            assertThat(view.isPressed()).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setSelected() {
        view.setSelected(true);
        Consumer<? super Boolean> action = RxView.setSelected(view);
        try {
            action.accept(false);
            assertThat(view.isSelected()).isFalse();
            action.accept(true);
            assertThat(view.isSelected()).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setVisibility() {
        view.setVisibility(View.VISIBLE);
        Consumer<? super Boolean> action = RxView.setVisibility(view);
        try {
            action.accept(false);
            assertThat(view.getVisibility()).isEqualTo(View.GONE);
            action.accept(true);
            assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @UiThreadTest
    public void setVisibilityCustomFalse() {
        view.setVisibility(View.VISIBLE);
        Consumer<? super Boolean> action = RxView.setVisibility(view, View.INVISIBLE);
        try {
            action.accept(false);
            assertThat(view.getVisibility()).isEqualTo(View.INVISIBLE);
            action.accept(true);
            assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

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
