package com.forceson.rxbinding.kotlin.view

import android.view.DragEvent
import android.view.View
import com.forceson.rxbinding.view.*
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-01-29.
 */

fun View.clicks(): Observable<Any> = RxView.clicks(this)

fun View.drags(): Observable<DragEvent> = RxView.drags(this)

fun View.focusChanges(): Observable<Boolean> = RxView.focusChanges(this)

fun View.longClicks(): Observable<Any> = RxView.longClicks(this)

fun View.activated(): Consumer<in Boolean> = RxView.setActivated(this)

fun View.clickable(): Consumer<in Boolean> = RxView.setClickable(this)

fun View.enabled(): Consumer<in Boolean> = RxView.setEnabled(this)

fun View.pressed(): Consumer<in Boolean> = RxView.setPressed(this)

fun View.selected(): Consumer<in Boolean> = RxView.setSelected(this)

fun View.visibility(visibilityWhenFalse: Int = View.GONE): Consumer<in Boolean> {
    return RxView.setVisibility(this, visibilityWhenFalse)
}