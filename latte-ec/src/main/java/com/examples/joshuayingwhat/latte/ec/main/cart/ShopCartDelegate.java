package com.examples.joshuayingwhat.latte.ec.main.cart;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * 购物车界面
 *
 * @author joshuayingwhat
 */
public class ShopCartDelegate extends BottomItemDelegate {
    @BindView(R2.id.tv_top_shop_cart_clear)
    AppCompatTextView tvTopShopCartClear;
    @BindView(R2.id.tv_top_shop_cart_remove_selected)
    AppCompatTextView tvTopShopCartRemoveSelected;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView rvShopCart;
    @BindView(R2.id.stub_no_item)
    ViewStub stubNoItem;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView iconShopCartSelectAll;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView tvShopCartTotalPrice;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView tvShopCartPay;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //请求购物车数据
        RestClient.builder().url("shop_cart.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .build().get();
    }
}
