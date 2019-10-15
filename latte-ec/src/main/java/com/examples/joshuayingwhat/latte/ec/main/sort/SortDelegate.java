package com.examples.joshuayingwhat.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.main.sort.content.ContentDelegate;
import com.examples.joshuayingwhat.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * 分类界面
 *
 * @author joshuayingwhat
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        final ContentDelegate contentDelegate = new ContentDelegate();
        loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧一个分类显示，默认选中第一个类别的内容
        replaceLoadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1),false);
    }

    /**
     * 当当前界面出现是才加载
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
}
