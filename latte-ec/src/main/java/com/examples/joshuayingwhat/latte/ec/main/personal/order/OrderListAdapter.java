package com.examples.joshuayingwhat.latte.ec.main.personal.order;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.examples.joshuayingwhat.latte.ec.R;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleFields;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleRecyclerAdapter;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * 订单数据适配器
 *
 * @author joshuayingwhat
 */
public class OrderListAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleVal = entity.getFields(MultipleFields.TITLE);
                final String timeVal = entity.getFields(OrderItemFields.TIME);
                final double priceVal = entity.getFields(OrderItemFields.PRICE);
                final String imageUrl = entity.getFields(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);

                title.setText(titleVal);
                price.setText("价格：" + String.valueOf(priceVal));
                time.setText("时间：" + timeVal);
                break;
            default:
                break;
        }
    }
}
