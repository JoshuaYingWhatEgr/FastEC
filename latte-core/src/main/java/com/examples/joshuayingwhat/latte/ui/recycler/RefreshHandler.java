package com.examples.joshuayingwhat.latte.ui.recycler;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        //要开始加载了
        REFRESH_LAYOUT.setRefreshing(true);

        //进行一些网络请求操作
    }

    @Override
    public void onRefresh() {

    }
}
