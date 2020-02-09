package com.forceson.rxbinding.kotlin.view

import android.view.DragEvent
import android.view.View
import com.forceson.rxbinding.view.*
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-01-29.
 */

fun View.clicks(view: View): Observable<Any> = RxView.clicks(view)

fun View.clicksEvents(view: View): Observable<ViewClickEvent> = RxView.clickEvents(view)

fun View.drags(view: View): Observable<DragEvent> = RxView.drags(view)

fun View.dragEvents(view: View): Observable<ViewDragEvent> = RxView.dragEvents(view)

fun View.focusChanges(view: View): Observable<Boolean> = RxView.focusChanges(view)

fun View.focusChangeEvents(view: View): Observable<ViewFocusChangeEvent> =
    RxView.focusChangeEvents(view)

fun View.longClicks(view: View): Observable<Any> = RxView.longClicks(view)

fun View.longClickEvents(view: View): Observable<ViewLongClickEvent> = RxView.longClickEvents(view)

fun View.activated(view: View): Action1<in Boolean> = RxView.setActivated(view)

fun View.clickable(view: View): Action1<in Boolean> = RxView.setClickable(view)

fun View.enabled(view: View): Action1<in Boolean> = RxView.setEnabled(view)

fun View.pressed(view: View): Action1<in Boolean> = RxView.setPressed(view)

fun View.selected(view: View): Action1<in Boolean> = RxView.setSelected(view)

fun View.visibility(view: View, visibilityWhenFalse: Int = View.GONE): Action1<in Boolean> {
    return RxView.setVisibility(view, visibilityWhenFalse)
}