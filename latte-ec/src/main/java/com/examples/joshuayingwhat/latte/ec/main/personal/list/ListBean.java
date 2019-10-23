package com.examples.joshuayingwhat.latte.ec.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;

    public String mImageUrl = null;

    private String mText = null;

    private String mValue = null;

    private int mId = 0;

    private LatteDelegate mDelegate = null;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    private ListBean(int mItemType, String mImageUrl, String mText, String mValue, int mId, LatteDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public int getmItemType() {
        return mItemType;
    }

    public void setmItemType(int mItemType) {
        this.mItemType = mItemType;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public LatteDelegate getmDelegate() {
        return mDelegate;
    }

    public void setmDelegate(LatteDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setmOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static final class Builder {

        private int mItemType = 0;

        private String mImageUrl = null;

        private String mText = null;

        private String mValue = null;

        private int mId = 0;

        private LatteDelegate mDelegate = null;

        private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;


        public Builder setmItemType(int mItemType) {
            this.mItemType = mItemType;
            return this;
        }

        public Builder setmImageUrl(String mImageUrl) {
            this.mImageUrl = mImageUrl;
            return this;
        }

        public Builder setmText(String mText) {
            this.mText = mText;
            return this;
        }

        public Builder setmValue(String mValue) {
            this.mValue = mValue;
            return this;
        }

        public Builder setmId(int mId) {
            this.mId = mId;
            return this;
        }

        public Builder setmDelegate(LatteDelegate mDelegate) {
            this.mDelegate = mDelegate;
            return this;
        }

        public Builder setmOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
            this.mOnCheckedChangeListener = mOnCheckedChangeListener;
            return this;
        }

        public ListBean build() {
            return new ListBean(mItemType, mImageUrl, mText, mValue, mId, mDelegate, mOnCheckedChangeListener);
        }
    }
}
