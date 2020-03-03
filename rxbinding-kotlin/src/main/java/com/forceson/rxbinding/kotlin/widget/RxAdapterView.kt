package com.forceson.rxbinding.kotlin.widget

import android.widget.Adapter
import android.widget.AdapterView
import com.forceson.rxbinding.widget.AdapterViewSelectionEvent
import com.forceson.rxbinding.widget.RxAdapterView
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun <T: Adapter> AdapterView<T>.itemSelections(): Observable<Int> = RxAdapterView.itemSelections(this)

fun <T: Adapter> AdapterView<T>.selectionEvents(): Observable<AdapterViewSelectionEvent> = RxAdapterView.selectionEvents(this)

fun <T: Adapter> AdapterView<T>.itemClicks(): Observable<Int> = RxAdapterView.itemClicks(this)

fun <T: Adapter> AdapterView<T>.clickEvents(): Observable<AdapterViewItemClickEvent> = RxAdapterView.itemClickEvents(this)

fun <T: Adapter> AdapterView<T>.itemLongClicks(): Observable<Int> = RxAdapterView.itemLongClicks(this)

fun <T: Adapter> AdapterView<T>.longClickEvents(): Observable<AdapterViewItemLongClickEvent> = RxAdapterView.itemLongClicksEvents(this)

fun <T: Adapter> AdapterView<T>.selection(): Action1<in Int> = RxAdapterView.setSelection(this)