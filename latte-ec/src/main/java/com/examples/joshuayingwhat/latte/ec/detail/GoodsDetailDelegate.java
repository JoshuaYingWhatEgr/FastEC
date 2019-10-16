package com.examples.joshuayingwhat.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 商品详情界面
 * @author joshuayingwhat
 */
public class GoodsDetailDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_detail_goods;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    public static GoodsDetailDelegate creator() {
        return new GoodsDetailDelegate();
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
