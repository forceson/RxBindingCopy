package com.forceson.rxbinding.kotlin.widget

import android.widget.RadioGroup
import com.forceson.rxbinding.widget.RxRadioGroup
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun RadioGroup.checkedChanges(): Observable<Int> = RxRadioGroup.checkedChanges(this)

fun RadioGroup.checkedChangeEvents(): Observable<RadioGroupCheckedChangeEvent> = RxRadioGroup.checkedChangeEvents(this)

fun RadioGroup.checked(): Action1<in Int> = RxRadioGroup.setChecked(this)