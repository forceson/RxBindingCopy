package com.forceson.rxbinding.kotlin.widget

import android.widget.CompoundButton
import com.forceson.rxbinding.widget.RxCompoundButton
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-02-10.
 */

fun CompoundButton.checkedChanges(): Observable<Boolean> = RxCompoundButton.checkedChanges(this)

fun CompoundButton.checked(): Consumer<in Boolean> = RxCompoundButton.setChecked(this)

fun CompoundButton.toggle(): Consumer<in Any> = RxCompoundButton.toggle(this)