package com.forceson.rxbinding.content;

import android.content.SharedPreferences;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Created by son on 2020-01-13.
 */
public class RxSharedPreferences {

    private SharedPreferences sharedPreferences;
    private Observable<String> changedKeys;

    public static RxSharedPreferences create(SharedPreferences sharedPreferences) {
        return new RxSharedPreferences(sharedPreferences);
    }

    private RxSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.changedKeys = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        subscriber.onNext(key);
                    }
                };

                Subscription subscription = Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
                    }
                });
                subscriber.add(subscription);

                sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
            }
        }).share();
    }

    public Observable<String> getString(String key) {
        return getString(key, null);
    }

    public Observable<String> getString(String key, String defaultValue) {
        return observeKey(key).map(new Func1<String, String>() {
            @Override
            public String call(String changedKey) {
                return sharedPreferences.getString(changedKey, defaultValue);
            }
        });
    }

    public Action1<String> setString(final String key) {
        return new Action1<String>() {
            @Override
            public void call(String value) {
                sharedPreferences.edit().putString(key, value).apply();
            }
        };
    }

    public Observable<Boolean> getBoolean(String key) {
        return getBoolean(key, false);
    }

    public Observable<Boolean> getBoolean(String key, boolean defaultValue) {
        return observeKey(key).map(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String value) {
                return sharedPreferences.getBoolean(key, defaultValue);
            }
        });
    }

    public Action1<Boolean> setBoolean(final String key) {
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                sharedPreferences.edit().putBoolean(key, value).apply();
            }
        };
    }

    public Observable<Float> getFloat(String key) {
        return getFloat(key, 0);
    }

    public Observable<Float> getFloat(String key, float defaultValue) {
        return observeKey(key).map(new Func1<String, Float>() {
            @Override
            public Float call(String changedKey) {
                return sharedPreferences.getFloat(changedKey, defaultValue);
            }
        });
    }

    public Action1<Float> setFloat(final String key) {
        return new Action1<Float>() {
            @Override
            public void call(Float value) {
                sharedPreferences.edit().putFloat(key, value).apply();
            }
        };
    }

    public Observable<Integer> getInt(String key) {
        return getInt(key, 0);
    }

    public Observable<Integer> getInt(String key, int defaultValue) {
        return observeKey(key).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String changedKey) {
                return sharedPreferences.getInt(changedKey, defaultValue);
            }
        });
    }

    public Action1<Integer> setInt(String key) {
        return new Action1<Integer>() {
            @Override
            public void call(Integer value) {
                sharedPreferences.edit().putInt(key, value).apply();
            }
        };
    }

    private Observable<String> observeKey(final String key) {
        return changedKeys
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String value) {
                        return key.equals(value);
                    }
                })
                .startWith(key);
    }
}
