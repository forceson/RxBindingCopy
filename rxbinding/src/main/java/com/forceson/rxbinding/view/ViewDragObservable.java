package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import io.reactivex.functions.Function;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

/**
 * Created by son on 2020-01-10.
 */
public class ViewDragObservable extends Observable<DragEvent> {
    private final View view;
    private final Function<? super DragEvent, Boolean> handled;

    ViewDragObservable(View view, Function<? super DragEvent, Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    protected void subscribeActual(Observer<? super DragEvent> observer) {
        verifyMainThread();
        Listener listener = new Listener(view, handled, observer);
        observer.onSubscribe(listener);
        view.setOnDragListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements View.OnDragListener {
        private final View view;
        private final Function<? super DragEvent, Boolean> handled;
        private final Observer<? super DragEvent> observer;

        Listener(View view, Function<? super DragEvent, Boolean> handled, Observer<? super DragEvent> observer) {
            this.view = view;
            this.handled = handled;
            this.observer = observer;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (!isDisposed()) {
                try {
                    if (handled.apply(event)) {
                        observer.onNext(event);
                        return true;
                    }
                } catch (Exception e) {
                    observer.onError(e);
                    dispose();
                }
            }
            return false;
        }

        @Override
        protected void onDispose() {
            view.setOnDragListener(null);
        }
    }
}
