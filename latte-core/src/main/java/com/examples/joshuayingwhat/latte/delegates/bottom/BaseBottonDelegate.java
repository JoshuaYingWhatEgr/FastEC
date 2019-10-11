package com.examples.joshuayingwhat.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author joshuayingwhat
 */
public abstract class BaseBottonDelegate extends LatteDelegate {

    private final ArrayList<BottomTableBean> TABLE_BEANS = new ArrayList<>();

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<BottomTableBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;

    /**
     * 控制第一个展示的是那个fragment
     */
    private int mIndexDelegate = 0;

    /**
     * 点击bottom之后的颜色
     */
    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTableBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTableBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTableBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTableBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TABLE_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }
}
