package com.examples.joshuayingwhat.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;
import com.joshuayingwhat.latte_ui.ui.recycler.DataConverer;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleFields;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class SearchDataConvert extends DataConverer {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(SearchEnum.SEATCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
