package com.forceson.rxbinding.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewEvent<T extends View> {
    @NonNull
    private final T view;
    private final long timestamp;

    protected ViewEvent(@NonNull T view, long timestamp) {
        this.view = checkNotNull(view, "view == null");
        this.timestamp = timestamp;
    }

    public @NonNull
    T view() {
        return view;
    }

    public long timestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        final long timestamp = this.timestamp;
        return view.hashCode() * 37 + (int) (timestamp ^ (timestamp >>> 32));
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;
        if (!(obj instanceof ViewEvent)) return false;
        ViewEvent other = (ViewEvent) obj;
        return view == other.view && timestamp == other.timestamp;
    }
}
