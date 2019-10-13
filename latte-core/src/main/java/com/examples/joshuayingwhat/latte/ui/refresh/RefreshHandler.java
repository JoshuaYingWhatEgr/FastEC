package com.examples.joshuayingwhat.latte.ui.refresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;

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

    public void firstPage(String url) {
        RestClient.builder().url(url)
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //请求成功关闭下拉刷新
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }).build().get();
    }

    @Override
    public void onRefresh() {

    }
}
