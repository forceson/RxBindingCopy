package com.forceson.rxbinding;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.widget.RxRadioGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.functions.Consumer;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-25.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class RxRadioGroupTest {
    private final Context context = ApplicationProvider.getApplicationContext();
    private final RadioGroup view = new RadioGroup(context);

    @Before
    public void setUp() {
        RadioButton button1 = new RadioButton(context);
        button1.setId(1);
        view.addView(button1);
        RadioButton button2 = new RadioButton(context);
        button1.setId(2);
        view.addView(button2);
    }

    @Test
    @UiThreadTest
    public void checkedChanges() {
        RecordingObserver<Integer> o = new RecordingObserver<>();
        RxRadioGroup.checkedChanges(view).subscribe(o);
        o.assertNoMoreEvents();

        view.check(1);
        assertThat(o.takeNext()).isEqualTo(1);

        view.clearCheck();
        assertThat(o.takeNext()).isEqualTo(-1);

        view.check(2);
        assertThat(o.takeNext()).isEqualTo(2);

        o.dispose();

        view.check(1);
        o.assertNoMoreEvents();
    }

    @Test
    @UiThreadTest
    public void setChecked() {
        Consumer<? super Integer> action = RxRadioGroup.setChecked(view);
        try {
            assertThat(view.getCheckedRadioButtonId()).isEqualTo(-1);
            action.accept(1);
            assertThat(view.getCheckedRadioButtonId()).isEqualTo(1);
            action.accept(-1);
            assertThat(view.getCheckedRadioButtonId()).isEqualTo(-1);
            action.accept(2);
            assertThat(view.getCheckedRadioButtonId()).isEqualTo(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
