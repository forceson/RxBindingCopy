package com.forceson.rxbinding.kotlin.widget

import android.widget.ProgressBar
import com.forceson.rxbinding.widget.RxProgressBar
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun ProgressBar.incrementProgress(): Action1<in Int> = RxProgressBar.incrementProgressBy(this)

fun ProgressBar.incrementSecondaryProgress(): Action1<in Int> = RxProgressBar.incrementSecondaryProgressBy(this)

fun ProgressBar.indeterminate(): Action1<in Boolean> = RxProgressBar.setIndeterminate(this)

fun ProgressBar.max(): Action1<in Int> = RxProgressBar.setMax(this)

fun ProgressBar.progress(): Action1<in Int> = RxProgressBar.setProgress(this)

fun ProgressBar.secondaryProgress(): Action1<in Int> = RxProgressBar.setSecondaryProgress(this)