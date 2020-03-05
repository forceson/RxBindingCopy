package com.forceson.rxbinding;

import android.app.Instrumentation;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.forceson.rxbinding.widget.AdapterViewItemSelectionEvent;
import com.forceson.rxbinding.widget.AdapterViewNothingSelectionEvent;
import com.forceson.rxbinding.widget.AdapterViewSelectionEvent;
import com.forceson.rxbinding.widget.RxAdapterView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-21.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class RxAdapterViewTest {
    @Rule
    public final ActivityTestRule<RxAdapterViewTestActivity> activityRule = new ActivityTestRule<>(RxAdapterViewTestActivity.class);

    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

    private RxAdapterViewTestActivity activity;
    private Spinner spinner;
    private ListView listView;

    @Before
    public void setUp() {
        activity = activityRule.getActivity();
        spinner = activity.spinner;
        listView = activity.listView;
    }

    @Test
    public void itemSelections() {
        RecordingObserver<Integer> o = new RecordingObserver<>();
        RxAdapterView.itemSelections(spinner)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(o);
        assertThat(o.takeNext()).isEqualTo(0);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(2);
            }
        });
        assertThat(o.takeNext()).isEqualTo(2);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(0);
            }
        });
        assertThat(o.takeNext()).isEqualTo(0);

        o.dispose();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(1);
            }
        });
        o.assertNoMoreEvents();
    }

    @Test
    public void selectionEvents() {
        RecordingObserver<AdapterViewSelectionEvent> o = new RecordingObserver<>();
        RxAdapterView.selectionEvents(spinner)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(o);
        AdapterViewItemSelectionEvent event1 = (AdapterViewItemSelectionEvent) o.takeNext();
        assertThat(event1.view()).isSameAs(spinner);
        assertThat(event1.selectedView()).isNotNull();
        assertThat(event1.position()).isEqualTo(0);
        assertThat(event1.id()).isEqualTo(0);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(2);
            }
        });

        AdapterViewItemSelectionEvent event2 = (AdapterViewItemSelectionEvent) o.takeNext();
        assertThat(event2.view()).isSameAs(spinner);
        assertThat(event2.selectedView()).isNotNull();
        assertThat(event2.position()).isEqualTo(2);
        assertThat(event2.id()).isEqualTo(2);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity.values.clear();
                activity.adapter.notifyDataSetChanged();
            }
        });

        assertThat(o.takeNext()).isEqualTo(AdapterViewNothingSelectionEvent.create(spinner));

        o.dispose();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity.values.add("Hello");
                activity.adapter.notifyDataSetChanged();
            }
        });
        o.assertNoMoreEvents();
    }

    @Test
    public void setSelection() {
        final Consumer<? super Integer> action = RxAdapterView.setSelection(spinner);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                try {
                    action.accept(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                assertThat(spinner.getSelectedItemPosition()).isEqualTo(2);
            }
        });

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                try {
                    action.accept(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                assertThat(spinner.getSelectedItemPosition()).isEqualTo(1);
            }
        });
    }

    @Test
    public void itemLongClicks() {
        // TODO
    }

    @Test
    public void itemLongClickEvents() {
        // TODO
    }
}
