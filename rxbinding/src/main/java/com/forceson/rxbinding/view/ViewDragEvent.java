package com.forceson.rxbinding.view;

import android.view.DragEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public class ViewDragEvent extends ViewEvent<View> {
    public static ViewDragEvent create(View view, DragEvent dragEvent) {
        return new ViewDragEvent(view, dragEvent);
    }

    private final DragEvent dragEvent;

    private ViewDragEvent(View view, DragEvent dragEvent) {
        super(view);
        this.dragEvent = checkNotNull(dragEvent, "dragEvent == null");
    }

    public DragEvent dragEvent() {
        return dragEvent;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + view().hashCode();
        result = result * 37 + dragEvent.hashCode();
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ViewDragEvent)) return false;
        ViewDragEvent other = (ViewDragEvent) obj;
        return other.view() == view() && other.dragEvent.equals(dragEvent);
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewDragEvent{dragEvent=" + dragEvent + ", view=" + view() + '}';
    }
}
