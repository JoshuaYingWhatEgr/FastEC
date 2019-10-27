package com.joshuayingwhat.latte_ui.ui.refresh;

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

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder().url(url)
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
        //要开始加载了
        REFRESH_LAYOUT.setRefreshing(true);

        //进行一些网络请求操作
        firstPage("index.php");
    }

    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }

    /***
     * 设置分页
     * @param url
     */
    private void paging(String url) {
        final int pageSize = BEAN.getPageSize();
        final int current = BEAN.getCurrentCount();
        final int totla = BEAN.getTotle();
        //当前的页码数
        final int inde = BEAN.getPageIndex();
        if (multipleRecyclerAdapter.getData().size() < pageSize || current >= totla) {
            multipleRecyclerAdapter.loadMoreEnd(true);
        } else {
            //请求服务器获取其他页的内容
            RestClient.builder().url(url + inde)
                    .loader(RECYCLERVIEW.getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            multipleRecyclerAdapter.addData(CONVERER.setJsonData(response).convert());
                            //累加数量
                            BEAN.setCurrentCount(multipleRecyclerAdapter.getData().size());
                            multipleRecyclerAdapter.loadMoreComplete();//加载结束
                            BEAN.addIndex();
                        }
                    }).build().get();
        }
    }
}
