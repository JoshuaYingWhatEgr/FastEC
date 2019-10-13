package com.examples.joshuayingwhat.latte.ec.main;

import android.graphics.Color;

import com.examples.joshuayingwhat.latte.delegates.bottom.BaseBottomDelegate;
import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.delegates.bottom.BottomTableBean;
import com.examples.joshuayingwhat.latte.delegates.bottom.ItemBuilder;
import com.examples.joshuayingwhat.latte.ec.main.index.IndexDelegate;
import com.examples.joshuayingwhat.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTableBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTableBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTableBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTableBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTableBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTableBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTableBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    //启识页面
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
