package com.forceson.rxbinding.kotlin.view

import android.view.DragEvent
import android.view.View
import com.forceson.rxbinding.view.*
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-01-29.
 */

fun View.clicks(): Observable<Any> = RxView.clicks(this)

fun View.clicksEvents(): Observable<ViewClickEvent> = RxView.clickEvents(this)

fun View.drags(): Observable<DragEvent> = RxView.drags(this)

fun View.dragEvents(): Observable<ViewDragEvent> = RxView.dragEvents(this)

fun View.focusChanges(): Observable<Boolean> = RxView.focusChanges(this)

fun View.focusChangeEvents(): Observable<ViewFocusChangeEvent> = RxView.focusChangeEvents(this)

fun View.longClicks(): Observable<Any> = RxView.longClicks(this)

fun View.longClickEvents(): Observable<ViewLongClickEvent> = RxView.longClickEvents(this)

fun View.activated(): Action1<in Boolean> = RxView.setActivated(this)

fun View.clickable(): Action1<in Boolean> = RxView.setClickable(this)

fun View.enabled(): Action1<in Boolean> = RxView.setEnabled(this)

fun View.pressed(): Action1<in Boolean> = RxView.setPressed(this)

fun View.selected(): Action1<in Boolean> = RxView.setSelected(this)

fun View.visibility(visibilityWhenFalse: Int = View.GONE): Action1<in Boolean> {
    return RxView.setVisibility(this, visibilityWhenFalse)
}