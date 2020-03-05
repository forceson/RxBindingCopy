package com.forceson.rxbinding.kotlin.widget

import android.widget.Adapter
import android.widget.AdapterView
import com.forceson.rxbinding.widget.AdapterViewSelectionEvent
import com.forceson.rxbinding.widget.RxAdapterView
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by son on 2020-02-10.
 */

fun <T: Adapter> AdapterView<T>.itemSelections(): Observable<Int> = RxAdapterView.itemSelections(this)

fun <T: Adapter> AdapterView<T>.selectionEvents(): Observable<AdapterViewSelectionEvent> = RxAdapterView.selectionEvents(this)

fun <T: Adapter> AdapterView<T>.itemLongClicks(): Observable<Int> = RxAdapterView.itemLongClicks(this)

fun <T: Adapter> AdapterView<T>.selection(): Consumer<in Int> = RxAdapterView.setSelection(this)