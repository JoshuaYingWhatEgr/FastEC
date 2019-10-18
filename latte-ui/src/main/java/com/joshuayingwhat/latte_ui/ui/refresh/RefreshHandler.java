package com.examples.joshuayingwhat.latte.ui.refresh;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.joshuayingwhat.latte_ui.ui.recycler.DataConverer;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleRecyclerAdapter;
import com.joshuayingwhat.latte_ui.ui.refresh.PagingBean;

/**
 * 刷新助手
 *
 * @author joshuayingwhat
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    private final PagingBean BEAN;

    private final RecyclerView RECYCLERVIEW;

    private MultipleRecyclerAdapter multipleRecyclerAdapter = null;

    private final DataConverer CONVERER;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          PagingBean bean, RecyclerView recyclerView, DataConverer converer) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERER = converer;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler creator(SwipeRefreshLayout swipeRefreshLayout,
                                         RecyclerView recyclerView, DataConverer converer) {

        return new RefreshHandler(swipeRefreshLayout, new PagingBean(), recyclerView, converer);
    }

    private void refresh() {
        //要开始加载了
        REFRESH_LAYOUT.setRefreshing(true);

        //进行一些网络请求操作

    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder().url(url)
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //请求成功关闭下拉刷新
                        REFRESH_LAYOUT.setRefreshing(false);
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotle(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));

                        //设置adapter
                        multipleRecyclerAdapter = MultipleRecyclerAdapter.create(CONVERER.setJsonData(response));
                        multipleRecyclerAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(multipleRecyclerAdapter);
                        BEAN.addIndex();
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

    @Override
    public void onLoadMoreRequested() {

    }
}
