package com.forceson.rxbinding.kotlin.widget

import android.widget.ProgressBar
import com.forceson.rxbinding.widget.RxProgressBar
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun ProgressBar.incrementProgress(view: ProgressBar): Action1<in Int> = RxProgressBar.incrementProgressBy(view)

fun ProgressBar.incrementSecondaryProgress(view: ProgressBar): Action1<in Int> = RxProgressBar.incrementSecondaryProgressBy(view)

fun ProgressBar.indeterminate(view: ProgressBar): Action1<in Boolean> = RxProgressBar.setIndeterminate(view)

fun ProgressBar.max(view: ProgressBar): Action1<in Int> = RxProgressBar.setMax(view)

fun ProgressBar.progress(view: ProgressBar): Action1<in Int> = RxProgressBar.setProgress(view)

fun ProgressBar.secondaryProgress(view: ProgressBar): Action1<in Int> = RxProgressBar.setSecondaryProgress(view)