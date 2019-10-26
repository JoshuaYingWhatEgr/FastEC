package com.examples.joshuayingwhat.latte.ec.main.index.search;

import androidx.appcompat.widget.AppCompatTextView;

import com.examples.joshuayingwhat.latte.ec.R;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleFields;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleRecyclerAdapter;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleViewHolder;

import java.util.List;

public class SearchAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (item.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = item.getFields(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
