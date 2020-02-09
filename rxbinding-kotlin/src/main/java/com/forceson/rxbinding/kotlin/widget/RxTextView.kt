package com.forceson.rxbinding.kotlin.widget

import android.widget.TextView
import com.forceson.rxbinding.widget.RxTextView
import com.forceson.rxbinding.widget.TextViewTextChangeEvent
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun TextView.textChanges(view: TextView): Observable<CharSequence> = RxTextView.textChanges(view)

fun TextView.textChangeEvents(view: TextView): Observable<TextViewTextChangeEvent> = RxTextView.textChangeEvents(view)

fun TextView.text(view: TextView): Action1<in CharSequence> = RxTextView.setText(view)

fun TextView.textRes(view: TextView): Action1<in Int> = RxTextView.setTextRes(view)