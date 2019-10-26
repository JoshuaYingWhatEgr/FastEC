package com.examples.joshuayingwhat.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;

import butterknife.BindView;

public class GoodsInfoDelegate extends LatteDelegate {
    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;


    private static final String ARGS_GOODS_INFO = "args_goods_info";
    private JSONObject mData;

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    public static GoodsInfoDelegate create(String goodsInfo) {
        final Bundle args = new Bundle();
        args.putString(ARGS_GOODS_INFO, goodsInfo);
        GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            String goodsInfo = args.getString(ARGS_GOODS_INFO);
            mData = JSON.parseObject(goodsInfo);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(desc);
        mGoodsInfoPrice.setText(String.valueOf(price));
    }
}
