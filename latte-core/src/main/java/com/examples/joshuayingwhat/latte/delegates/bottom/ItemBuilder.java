package com.examples.joshuayingwhat.latte.delegates.bottom;

import java.util.LinkedHashMap;

public final class ItemBuilder {

    private final LinkedHashMap<BottomTableBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTableBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTableBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTableBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
