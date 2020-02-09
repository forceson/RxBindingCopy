package com.forceson.rxbinding.kotlin.widget

import android.widget.RadioGroup
import com.forceson.rxbinding.widget.RadioGroupCheckedChangeEvent
import com.forceson.rxbinding.widget.RxRadioGroup
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun RadioGroup.checkedChanges(view: RadioGroup): Observable<Int> = RxRadioGroup.checkedChanges(view)

fun RadioGroup.checkedChangeEvents(view: RadioGroup): Observable<RadioGroupCheckedChangeEvent> = RxRadioGroup.checkedChangeEvents(view)

fun RadioGroup.checked(view: RadioGroup): Action1<in Int> = RxRadioGroup.setChecked(view)