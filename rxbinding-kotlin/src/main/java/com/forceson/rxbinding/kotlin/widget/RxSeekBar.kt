package com.forceson.rxbinding.kotlin.widget

import android.widget.SeekBar
import com.forceson.rxbinding.widget.RxSeekBar
import com.forceson.rxbinding.widget.SeekBarChangeEvent
import rx.Observable

/**
 * Created by son on 2020-02-10.
 */

fun SeekBar.changes(view: SeekBar): Observable<Int> = RxSeekBar.changes(view)

fun SeekBar.changeEvents(view: SeekBar): Observable<SeekBarChangeEvent> = RxSeekBar.changeEvents(view)
