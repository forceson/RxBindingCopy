package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

/**
 * Created by son on 2020-01-24.
 */
public class AdapterViewItemLongClickObservable extends Observable<Integer> {
    private final AdapterView<?> view;
    private final Callable<Boolean> handled;

    AdapterViewItemLongClickObservable(AdapterView<?> view, Callable<Boolean> handled) {
        this.view = view;
        this.handled = handled;
    }

    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        verifyMainThread();
        Listener listener = new Listener(view, handled, observer);
        observer.onSubscribe(listener);
        view.setOnItemLongClickListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemLongClickListener {
        private final AdapterView<?> view;
        private final Callable<Boolean> handled;
        private final Observer<? super Integer> observer;

        Listener(AdapterView<?> view, Callable<Boolean> handled, Observer<? super Integer> observer) {
            this.view = view;
            this.handled = handled;
            this.observer = observer;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if (!isDisposed()) {
                try {
                    if (handled.call()) {
                        observer.onNext(position);
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
            view.setOnItemLongClickListener(null);
        }
    }
}
