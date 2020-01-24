package com.forceson.rxbinding;

import android.app.Instrumentation;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.forceson.rxbinding.schedulers.HandlerSchedulers;
import com.forceson.rxbinding.widget.AdapterViewItemClickEvent;
import com.forceson.rxbinding.widget.AdapterViewItemSelectionEvent;
import com.forceson.rxbinding.widget.AdapterViewNothingSelectionEvent;
import com.forceson.rxbinding.widget.AdapterViewSelectionEvent;
import com.forceson.rxbinding.widget.RxAdapterView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscription;
import rx.functions.Action1;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-21.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class RxAdapterViewTest {
    @Rule
    public final ActivityTestRule<RxAdapterViewActivity> activityRule = new ActivityTestRule<>(RxAdapterViewActivity.class);

    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

    @Test
    public void itemSelections() {
        RxAdapterViewActivity activity = activityRule.getActivity();
        final Spinner spinner = activity.spinner;

        RecordingObserver<Integer> o = new RecordingObserver<>();
        Subscription subscription = RxAdapterView.itemSelections(spinner)
                .subscribeOn(HandlerSchedulers.mainThread())
                .subscribe(o);
        o.assertNoMoreEvents();

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

        subscription.unsubscribe();

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
        final RxAdapterViewActivity activity = activityRule.getActivity();
        final Spinner spinner = activity.spinner;

        RecordingObserver<AdapterViewSelectionEvent> o = new RecordingObserver<>();
        Subscription subscription = RxAdapterView.selectionEvents(spinner)
                .subscribeOn(HandlerSchedulers.mainThread())
                .subscribe(o);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(2);
            }
        });

        AdapterViewItemSelectionEvent event = (AdapterViewItemSelectionEvent) o.takeNext();
        assertThat(event.view()).isSameAs(spinner);
        assertThat(event.selectedView()).isNotNull();
        assertThat(event.position()).isEqualTo(2);
        assertThat(event.id()).isEqualTo(2);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity.values.clear();
                activity.adapter.notifyDataSetChanged();
            }
        });

        assertThat(o.takeNext()).isEqualTo(AdapterViewNothingSelectionEvent.create(spinner));

        subscription.unsubscribe();

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
        RxAdapterViewActivity activity = activityRule.getActivity();
        final Spinner spinner = activity.spinner;

        final Action1<? super Integer> action = RxAdapterView.setSelection(spinner);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                action.call(2);
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
                action.call(1);
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
    public void itemClicks() {
        RxAdapterViewActivity activity = activityRule.getActivity();
        final ListView listView = activity.listView;

        RecordingObserver<Integer> o = new RecordingObserver<>();
        Subscription subscription = RxAdapterView.itemClicks(listView)
                .subscribeOn(HandlerSchedulers.mainThread())
                .subscribe(o);
        o.assertNoMoreEvents();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(2), 2, 2);
            }
        });
        assertThat(o.takeNext()).isEqualTo(2);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(0), 0, 0);
            }
        });
        assertThat(o.takeNext()).isEqualTo(0);

        subscription.unsubscribe();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(1), 1, 1);
            }
        });
        o.assertNoMoreEvents();
    }

    @Test
    public void itemClickEvents() {
        RxAdapterViewActivity activity = activityRule.getActivity();
        final ListView listView = activity.listView;

        RecordingObserver<AdapterViewItemClickEvent> o = new RecordingObserver<>();
        Subscription subscription = RxAdapterView.itemClickEvents(listView) //
                .subscribeOn(HandlerSchedulers.mainThread()) //
                .subscribe(o);
        o.assertNoMoreEvents();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(2), 2, 2);
            }
        });
        AdapterViewItemClickEvent event = o.takeNext();
        assertThat(event.view()).isEqualTo(listView);
        assertThat(event.clickedView()).isNotNull();
        assertThat(event.position()).isEqualTo(2);
        assertThat(event.id()).isEqualTo(2);

        subscription.unsubscribe();

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(1), 1, 1);
            }
        });
        o.assertNoMoreEvents();
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
