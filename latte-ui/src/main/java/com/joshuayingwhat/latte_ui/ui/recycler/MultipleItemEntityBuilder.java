package com.joshuayingwhat.latte_ui.ui.recycler;

import java.util.LinkedHashMap;

public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        //清空之前的数据
        FIELDS.clear();
    }

    public MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(com.examples.joshuayingwhat.latte.ui.recycler.MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> maps) {
        FIELDS.putAll(maps);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
