package com.forceson.rxbinding;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.forceson.rxbinding.plugins.RxAndroidLogHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;
import com.forceson.rxbinding.util.RxLog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by son on 2020-01-18.
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public final class RxLogTest {
    @Rule
    public final PluginResetRule pluginResetRule = new PluginResetRule();

    private final List<String> logs = new ArrayList<>();

    @Before
    public void setUp() {
        RxAndroidPlugins.getInstance().registerLogHook(new RxAndroidLogHook() {
            @Override
            public void log(int priority, String tag, String msg, Throwable throwable) {
                if (msg == null) {
                    msg = "null";
                }
                if (throwable != null) {
                    msg += " " + throwable.getClass().getSimpleName();
                }
                logs.add(priority + " " + tag + " " + msg);
            }
        });
    }

    @Test
    public void levels() {
        RxLog.v("Tag").call("Hi");
        RxLog.v("Tag").call(true);
        RxLog.v("Tag").call(null);
        RxLog.v("Tag").call(0.2);
        RxLog.d("Tag").call("Hi");
        RxLog.d("Tag").call(true);
        RxLog.d("Tag").call(null);
        RxLog.d("Tag").call(0.2);
        RxLog.i("Tag").call("Hi");
        RxLog.i("Tag").call(true);
        RxLog.i("Tag").call(null);
        RxLog.i("Tag").call(0.2);
        RxLog.w("Tag").call("Hi");
        RxLog.w("Tag").call(true);
        RxLog.w("Tag").call(null);
        RxLog.w("Tag").call(0.2);
        RxLog.e("Tag").call("Hi");
        RxLog.e("Tag").call(true);
        RxLog.e("Tag").call(null);
        RxLog.e("Tag").call(0.2);

        assertThat(logs).containsExactly( //
                "2 Tag Hi", "2 Tag true", "2 Tag null", "2 Tag 0.2", //
                "3 Tag Hi", "3 Tag true", "3 Tag null", "3 Tag 0.2", //
                "4 Tag Hi", "4 Tag true", "4 Tag null", "4 Tag 0.2", //
                "5 Tag Hi", "5 Tag true", "5 Tag null", "5 Tag 0.2", //
                "6 Tag Hi", "6 Tag true", "6 Tag null", "6 Tag 0.2" //
        );
    }

    @Test
    public void errors() {
        RxLog.error("Tag").call(new RuntimeException());
        RxLog.error("Tag", "Message").call(new RuntimeException());

        assertThat(logs).containsExactly( //
            "6 Tag null RuntimeException", //
            "6 Tag Message RuntimeException" //
        );
    }
}
