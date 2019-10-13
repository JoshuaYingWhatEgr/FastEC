package com.examples.joshuayingwhat.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.ui.recycler.DataConverer;
import com.examples.joshuayingwhat.latte.ui.recycler.ItemType;
import com.examples.joshuayingwhat.latte.ui.recycler.MultipleItemEntity;
import com.examples.joshuayingwhat.latte.ui.recycler.MutilpleFields;

import java.util.ArrayList;

public class IndexDataConvert extends DataConverer {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSONObject.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MutilpleFields.ITEM_TYPE,type)
                    .setField(MutilpleFields.SPAN_SIZE,spanSize)
                    .setField(MutilpleFields.ID,id)
                    .setField(MutilpleFields.TEXT,text)
                    .setField(MutilpleFields.IMAGE_URL,imageUrl)
                    .setField(MutilpleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
