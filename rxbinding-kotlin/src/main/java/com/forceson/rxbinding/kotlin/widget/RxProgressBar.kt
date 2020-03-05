package com.forceson.rxbinding.kotlin.widget

import android.widget.ProgressBar
import com.forceson.rxbinding.widget.RxProgressBar
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-02-10.
 */

fun ProgressBar.incrementProgress(): Consumer<in Int> = RxProgressBar.incrementProgressBy(this)

fun ProgressBar.incrementSecondaryProgress(): Consumer<in Int> = RxProgressBar.incrementSecondaryProgressBy(this)

fun ProgressBar.indeterminate(): Consumer<in Boolean> = RxProgressBar.setIndeterminate(this)

fun ProgressBar.max(): Consumer<in Int> = RxProgressBar.setMax(this)

fun ProgressBar.progress(): Consumer<in Int> = RxProgressBar.setProgress(this)

fun ProgressBar.secondaryProgress(): Consumer<in Int> = RxProgressBar.setSecondaryProgress(this)