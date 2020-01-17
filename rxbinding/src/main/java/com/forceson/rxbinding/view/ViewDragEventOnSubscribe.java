package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import com.forceson.rxbinding.internal.AndroidSubscriptions;
import com.forceson.rxbinding.plugins.RxAndroidClockHook;
import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-10.
 */
public class ViewDragEventOnSubscribe implements Observable.OnSubscribe<ViewDragEvent> {
    private final View view;
    private final Func1<? super ViewDragEvent, Boolean> handled;

    public ViewDragEventOnSubscribe(View view, Func1<? super ViewDragEvent, Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    public void call(Subscriber<? super ViewDragEvent> subscriber) {
        checkUiThread();

        View.OnDragListener listener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                ViewDragEvent event = ViewDragEvent.create(view, dragEvent);
                if (handled.call(event)) {
                    subscriber.onNext(event);
                    return true;
                }
                return false;
            }
        };
        Subscription subscription = AndroidSubscriptions.unsubscribeOnMainThread(new Action0() {
            @Override
            public void call() {
                view.setOnDragListener(null);
            }
        });
        subscriber.add(subscription);

        view.setOnDragListener(listener);
    }
}
