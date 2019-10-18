package com.examples.joshuayingwhat.latte.ec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.sort.SortDelegate;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * 垂直的侧边栏布局
 *
 * @author joshuayingwhat
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        //屏蔽动画效果
        mRecyclerView.setAnimation(null);
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //网络请求获取数据
//        initData();
    }

    /***
     * 获取分类列表
     */
    private void initData() {
        RestClient.builder().url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data = new VerticalListDataConvert().
                                setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter sortRecyclerAdapter = new SortRecyclerAdapter(data, delegate);

                        mRecyclerView.setAdapter(sortRecyclerAdapter);
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).build().get();
    }
}
