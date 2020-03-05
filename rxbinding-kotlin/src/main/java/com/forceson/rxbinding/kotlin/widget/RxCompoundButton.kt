package com.forceson.rxbinding.kotlin.widget

import android.widget.CompoundButton
import com.forceson.rxbinding.widget.RxCompoundButton
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun CompoundButton.checkedChanges(): Observable<Boolean> = RxCompoundButton.checkedChanges(this)

fun CompoundButton.checkChangeEvents(): Observable<CompoundButtonCheckedChangeEvent> = RxCompoundButton.checkedChangeEvents(this)

fun CompoundButton.checked(): Action1<in Boolean> = RxCompoundButton.setChecked(this)

fun CompoundButton.toggle(): Action1<in Any> = RxCompoundButton.toggle(this)