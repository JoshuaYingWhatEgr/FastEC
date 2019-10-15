package com.examples.joshuayingwhat.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

public class SectionBean extends SectionEntity<SectionContentEntity> {

    private boolean isMore = false;

    private int mId = -1;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentEntity sectionContentEntity) {
        super(sectionContentEntity);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
