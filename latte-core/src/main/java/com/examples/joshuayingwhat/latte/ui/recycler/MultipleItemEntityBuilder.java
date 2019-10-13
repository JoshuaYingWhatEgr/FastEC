package com.examples.joshuayingwhat.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        //清空之前的数据
        FIELDS.clear();
    }

    public MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MutilpleFields.ITEM_TYPE, itemType);
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
