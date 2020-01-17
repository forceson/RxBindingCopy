package com.forceson.rxbinding.widget;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by son on 2020-01-17.
 */
public class AdapterViewItemSelectionEvent extends AdapterViewSelectionEvent {
    public static AdapterViewItemSelectionEvent create(@NonNull AdapterView<?> view, View selectedView, int position, long id) {
        return new AdapterViewItemSelectionEvent(view, selectedView, position, id);
    }

    private final View selectedView;
    private final int position;
    private final long id;

    private AdapterViewItemSelectionEvent(@NonNull AdapterView<?> view, View selectedView, int position, long id) {
        super(view);
        this.selectedView = selectedView;
        this.position = position;
        this.id = id;
    }

    public View selectedView() {
        return selectedView;
    }

    public int position() {
        return position;
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AdapterViewItemSelectionEvent)) return false;
        AdapterViewItemSelectionEvent other = (AdapterViewItemSelectionEvent) o;
        return other.view() == view()
                && other.selectedView == selectedView
                && other.position == position
                && other.id == id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + view().hashCode();
        result = result * 37 + selectedView.hashCode();
        result = result * 37 + position;
        result = result * 37 + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AdapterViewItemSelectionEvent{view="
                + view()
                + ", selectedView="
                + selectedView
                + ", position="
                + position
                + ", id="
                + id
                + '}';
    }
}
