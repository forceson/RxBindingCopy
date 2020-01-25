package com.forceson.rxbinding;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

/**
 * Created by son on 2020-01-25.
 */
public final class RxSeekBarTestActivity extends Activity {
    SeekBar seekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seekBar = new SeekBar(this);
        setContentView(seekBar);
    }
}
