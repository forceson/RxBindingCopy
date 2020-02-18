package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import com.forceson.rxbinding.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static com.forceson.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by son on 2020-01-10.
 */
public class ViewDragOnSubscribe implements Observable.OnSubscribe<DragEvent> {
    private final View view;
    private final Func1<? super DragEvent, Boolean> handled;

    public ViewDragOnSubscribe(View view, Func1<? super DragEvent, Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    public void call(Subscriber<? super DragEvent> subscriber) {
        checkUiThread();

        View.OnDragListener listener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (handled.call(dragEvent)) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(dragEvent);
                    }
                    return true;
                }
                return false;
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnDragListener(null);
            }
        });

        view.setOnDragListener(listener);
    }
}
