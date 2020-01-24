package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.forceson.rxbinding.view.ViewEvent;

import org.jetbrains.annotations.NotNull;

/**
 * Created by son on 2020-01-24.
 */
public class AdapterViewItemLongClickEvent extends ViewEvent<AdapterView<?>> {

    public static AdapterViewItemLongClickEvent create(@NonNull AdapterView<?> view, View clickedView, int position, long id) {
        return new AdapterViewItemLongClickEvent(view, clickedView, position, id);
    }

    private final View clickedView;
    private final int position;
    private final long id;

    private AdapterViewItemLongClickEvent(@NonNull AdapterView<?> view, View clickedView, int position, long id) {
        super(view);
        this.clickedView = clickedView;
        this.position = position;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AdapterViewItemLongClickEvent)) return false;
        AdapterViewItemLongClickEvent other = (AdapterViewItemLongClickEvent) o;
        return other.view() == view()
                && other.clickedView == clickedView
                && other.position == position
                && other.id == id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + view().hashCode();
        result = result * 37 + clickedView.hashCode();
        result = result * 37 + position;
        result = result * 37 + (int) (id ^ (id >>> 32));
        return result;
    }

    @NotNull
    @Override
    public String toString() {
        return "AdapterViewItemLongClickEvent{view="
                + view()
                + ", clickedView="
                + clickedView
                + ", position="
                + position
                + ", id="
                + id
                + '}';
    }
}
