package com.examples.joshuayingwhat.latte.ec.main.personal.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.examples.joshuayingwhat.latte.ec.R;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

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
    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORAL, R.layout.arrow_item_layout);
        //头像的layout
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;
            case ListItemType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(item.getmImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
