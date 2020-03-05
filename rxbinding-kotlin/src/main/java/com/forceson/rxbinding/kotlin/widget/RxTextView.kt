package com.forceson.rxbinding.kotlin.widget

import android.widget.TextView
import com.forceson.rxbinding.widget.RxTextView
import com.forceson.rxbinding.widget.TextViewTextChangeEvent
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-02-10.
 */

fun TextView.textChanges(): Observable<CharSequence> = RxTextView.textChanges(this)

fun TextView.textChangeEvents(): Observable<TextViewTextChangeEvent> = RxTextView.textChangeEvents(this)

fun TextView.text(): Consumer<in CharSequence> = RxTextView.setText(this)

fun TextView.textRes(): Consumer<in Int> = RxTextView.setTextRes(this)