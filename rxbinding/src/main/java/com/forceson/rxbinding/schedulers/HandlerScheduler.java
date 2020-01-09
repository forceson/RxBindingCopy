package com.forceson.rxbinding.schedulers;

import android.os.Handler;

import com.forceson.rxbinding.plugins.RxAndroidPlugins;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by son on 2020-01-09.
 */
class HandlerScheduler extends Scheduler {
    private final Handler handler;

    HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new InnerHandlerThreadScheduler(handler);
    }

    private class InnerHandlerThreadScheduler extends Worker {
        private final Handler handler;
        private final CompositeSubscription compositeSubscription = new CompositeSubscription();

        public InnerHandlerThreadScheduler(Handler handler) {
            this.handler = handler;
        }

        @Override
        public Subscription schedule(Action0 action) {
            return schedule(action, 0, TimeUnit.MILLISECONDS);
        }

        @Override
        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            Action0 newAction = RxAndroidPlugins.getInstance().getSchedulersHook().onSchedule(action);
            if (newAction == null) {
                throw new NullPointerException(
                        "Registered schedulers hook returned null for action: " + action);
            }

            final ScheduledAction scheduledAction = new ScheduledAction(newAction);
            scheduledAction.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    handler.removeCallbacks(scheduledAction);
                }
            }));
            scheduledAction.addParent(compositeSubscription);
            compositeSubscription.add(scheduledAction);

            handler.postDelayed(scheduledAction, unit.toMillis(delayTime));

            return scheduledAction;
        }

        @Override
        public void unsubscribe() {
            compositeSubscription.unsubscribe();
        }

        @Override
        public boolean isUnsubscribed() {
            return compositeSubscription.isUnsubscribed();
        }
    }
}
