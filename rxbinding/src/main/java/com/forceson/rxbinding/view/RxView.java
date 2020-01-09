package com.forceson.rxbinding.view;

import android.view.View;

import rx.Observable;

/**
 * Created by son on 2020-01-09.
 */
public final class RxView {

    public static Observable<Long> clicks(View view) {
        return Observable.create(new ViewClickOnSubscribe(view));
    }
}
