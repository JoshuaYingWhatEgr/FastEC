package com.examples.joshuayingwhat.latte.ui.recycler;

import java.util.ArrayList;

/**
 * 数据的转换处理
 */
public abstract class DataConverer {

    protected ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonDta = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverer setJsonData(String json) {
        this.mJsonDta = json;
        return this;
    }

    public String getJsonData() {
        if (mJsonDta == null || mJsonDta.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonDta;
    }
}
