package com.forceson.rxbinding.kotlin.widget

import android.widget.Adapter
import android.widget.AdapterView
import com.forceson.rxbinding.widget.AdapterViewItemClickEvent
import com.forceson.rxbinding.widget.AdapterViewItemLongClickEvent
import com.forceson.rxbinding.widget.AdapterViewSelectionEvent
import com.forceson.rxbinding.widget.RxAdapterView
import rx.Observable
import rx.functions.Action1

/**
 * Created by son on 2020-02-10.
 */

fun <T: Adapter> AdapterView<T>.itemSelections(view: AdapterView<T>): Observable<Int> = RxAdapterView.itemSelections(view)

fun <T: Adapter> AdapterView<T>.selectionEvents(view: AdapterView<T>): Observable<AdapterViewSelectionEvent> = RxAdapterView.selectionEvents(view)

fun <T: Adapter> AdapterView<T>.itemClicks(view: AdapterView<T>): Observable<Int> = RxAdapterView.itemClicks(view)

fun <T: Adapter> AdapterView<T>.clickEvents(view: AdapterView<T>): Observable<AdapterViewItemClickEvent> = RxAdapterView.itemClickEvents(view)

fun <T: Adapter> AdapterView<T>.itemLongClicks(view: AdapterView<T>): Observable<Int> = RxAdapterView.itemLongClicks(view)

fun <T: Adapter> AdapterView<T>.longClickEvents(view: AdapterView<T>): Observable<AdapterViewItemLongClickEvent> = RxAdapterView.itemLongClicksEvents(view)

fun <T: Adapter> AdapterView<T>.selection(view: AdapterView<T>): Action1<in Int> = RxAdapterView.setSelection(view)