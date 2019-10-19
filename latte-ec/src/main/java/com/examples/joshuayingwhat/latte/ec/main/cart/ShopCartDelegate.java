package com.examples.joshuayingwhat.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.pay.FastPay;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 购物车界面
 *
 * @author joshuayingwhat
 */
public class ShopCartDelegate extends BottomItemDelegate implements ICartItemListener {

    //购物车数量标记
    private int mCurrentCount = 0;

    private int mTotalCount = 0;

    @BindView(R2.id.tv_top_shop_cart_clear)
    AppCompatTextView tvTopShopCartClear;
    @BindView(R2.id.tv_top_shop_cart_remove_selected)
    AppCompatTextView tvTopShopCartRemoveSelected;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView;
    @BindView(R2.id.stub_no_item)
    ViewStub mStubNoItem;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView tvShopCartPay;
    private ShopCartAdapter mAdapter;

    private double mTotalPrice;


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //请求购物车数据
//        RestClient.builder().url("shop_cart.php")
//                .loader(getContext())
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final ArrayList<MultipleItemEntity> data =
//                                new ShopCartDataConvert().setJsonData(response).convert();
//                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
//                        mRecyclerView.setLayoutManager(manager);
//                        mAdapter = new ShopCartAdapter(data);
//                        mRecyclerView.setAdapter(mAdapter);
//                        mTotalPrice = mAdapter.getmTotalPrice();
//                        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
//                    }
//                })
//                .build().get();
//
//        mAdapter.setmCartItemListener(this);

        checkItemCount();
    }

    @SuppressLint("ResourceType")
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll(View view) {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
        } else {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), Color.GRAY));
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
        }
        //更新recyclerview的显示状态
        mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem(View view) {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntitys = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getFields(ShopCartItemFields.IS_SELECTED.name());
            if (isSelected) {
                deleteEntitys.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntitys) {
            int removePosition;
            final int entityPosition = entity.getFields(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }

            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();
    }

    /**
     * 清空购物车
     *
     * @param view
     */
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickCartClear(View view) {
        mAdapter.getData().clear();
        mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        checkItemCount();
    }

    /**
     * 检查购物车是否有数据
     */
    private void checkItemCount() {
//        final int count = mAdapter.getItemCount();
//        if (count == 0) {
//            final View stubView = mStubNoItem.inflate();
//            final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
//            tvToBuy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), "您该购物了", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            mRecyclerView.setVisibility(View.GONE);
//        } else {
//            mRecyclerView.setVisibility(View.VISIBLE);
//        }
    }

    /**
     * 获取总价
     *
     * @param itemTotalPrice
     */
    @Override
    public void onItemClick(double itemTotalPrice) {
        /**
         * 获取购物车所有商品的总价
         */
        double totalPrice = mAdapter.getmTotalPrice();
        mTvTotalPrice.setText(String.valueOf(totalPrice));
    }

    /**
     * 结算
     */
    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay(View view) {
        FastPay.creater(this).beginPayDialog();
    }

    /**
     * 创建订单和支付还没有关系
     */
    private void createOrder() {
        final String orderUlr = "";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userId", 123456);
        orderParams.put("amout", "0.01");
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        //发起订单 将订单信息发送给服务器
        RestClient.builder().url(orderUlr)
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付

                    }
                }).build().post();
    }
}
