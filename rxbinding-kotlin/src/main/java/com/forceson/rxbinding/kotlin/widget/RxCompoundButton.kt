package com.forceson.rxbinding.kotlin.widget

import android.widget.CompoundButton
import com.forceson.rxbinding.widget.CompoundButtonCheckedChangeEvent
import com.forceson.rxbinding.widget.RxCompoundButton
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun CompoundButton.checkedChanges(view: CompoundButton): Observable<Boolean> = RxCompoundButton.checkedChanges(view)

fun CompoundButton.checkChangeEvents(view: CompoundButton): Observable<CompoundButtonCheckedChangeEvent> = RxCompoundButton.checkedChangeEvents(view)

fun CompoundButton.checked(view: CompoundButton): Action1<in Boolean> = RxCompoundButton.setChecked(view)

fun CompoundButton.toggle(view: CompoundButton): Action1<in Any> = RxCompoundButton.toggle(view)