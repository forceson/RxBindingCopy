package com.forceson.rxbinding.widget;

import android.widget.TextView;

import com.forceson.rxbinding.view.ViewEvent;

import static com.forceson.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by son on 2020-01-10.
 */
public final class TextViewTextChangeEvent extends ViewEvent<TextView> {
    public static TextViewTextChangeEvent create(TextView view, CharSequence text,
                                                 int start, int before, int count) {
        return new TextViewTextChangeEvent(view, text, start, before, count);
    }

    private final CharSequence text;
    private final int start;
    private final int before;
    private final int count;

    private TextViewTextChangeEvent(TextView view, CharSequence text, int start,
                                    int before, int count) {
        super(view);
        this.text = checkNotNull(text, "text == null");
        this.start = start;
        this.before = before;
        this.count = count;
    }

    public CharSequence text() {
        return text;
    }

    public int start() {
        return start;
    }

    public int before() {
        return before;
    }

    public int count() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TextViewTextChangeEvent)) return false;
        TextViewTextChangeEvent other = (TextViewTextChangeEvent) o;
        return super.equals(other)
                && text.equals(other.text)
                && start == other.start
                && before == other.before
                && count == other.count;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 37 + text.hashCode();
        result = result * 37 + start;
        result = result * 37 + before;
        result = result * 37 + count;
        return result;
    }

    @Override
    public String toString() {
        return "TextViewTextChangeEvent{text="
                + text
                + ", start="
                + start
                + ", before="
                + before
                + ", count="
                + count
                + ", view="
                + view()
                + '}';
    }
}