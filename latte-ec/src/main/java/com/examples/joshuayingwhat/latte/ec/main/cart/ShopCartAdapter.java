package com.examples.joshuayingwhat.latte.ec.main.cart;

import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ui.recycler.MultipleItemEntity;
import com.examples.joshuayingwhat.latte.ui.recycler.MultipleRecyclerAdapter;

import java.util.List;

/**
 * 购物车数据
 *
 * @author joshuayingwhat
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }
}
