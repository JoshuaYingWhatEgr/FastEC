package com.examples.joshuayingwhat.latte.ec.main.sort.content;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;

/**
 * 点击左侧分类栏目,contentdelegate切换不同的内容
 *
 * @author joshuayingwhat
 */
public class ContentDelegate extends LatteDelegate {

    public static final String ARG_CONTENT_ID = "CONTENT_ID";

    private int mContentId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(args);
        return contentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
