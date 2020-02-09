package com.forceson.rxbinding.kotlin.widget

import android.widget.TextView
import com.forceson.rxbinding.widget.RxTextView
import com.forceson.rxbinding.widget.TextViewTextChangeEvent
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun TextView.textChanges(): Observable<CharSequence> = RxTextView.textChanges(this)

fun TextView.textChangeEvents(): Observable<TextViewTextChangeEvent> = RxTextView.textChangeEvents(this)

fun TextView.text(): Action1<in CharSequence> = RxTextView.setText(this)

fun TextView.textRes(): Action1<in Int> = RxTextView.setTextRes(this)