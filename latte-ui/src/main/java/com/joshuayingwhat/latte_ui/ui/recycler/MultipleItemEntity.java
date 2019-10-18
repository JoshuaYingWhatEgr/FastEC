package com.joshuayingwhat.latte_ui.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntityBuilder;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();

    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();

    /**
     * 这里使用软引用是为了防止LinkedHashMap占用的内存过大导致oom
     */
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUENE);


    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    @Override
    public int getItemType() {

        return (int) FIELDS_REFERENCE.get().get(com.examples.joshuayingwhat.latte.ui.recycler.MultipleFields.ITEM_TYPE);
    }

    public final <T> T getFields(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }
}
