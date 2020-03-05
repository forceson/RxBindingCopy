package com.forceson.rxbinding.kotlin.widget

import android.widget.RadioGroup
import com.forceson.rxbinding.widget.RxRadioGroup
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-02-10.
 */

fun RadioGroup.checkedChanges(): Observable<Int> = RxRadioGroup.checkedChanges(this)

fun RadioGroup.checked(): Consumer<in Int> = RxRadioGroup.setChecked(this)