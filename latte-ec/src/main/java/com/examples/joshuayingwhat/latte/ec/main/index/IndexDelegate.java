package com.examples.joshuayingwhat.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.EcBottomDelegate;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackManager;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackType;
import com.examples.joshuayingwhat.latte.utils.callback.IGlobalCallBack;
import com.joanzapata.iconify.widget.IconTextView;
import com.joshuayingwhat.latte_ui.ui.recycler.BaseDecoration;
import com.joshuayingwhat.latte_ui.ui.refresh.RefreshHandler;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 这里是主页界面
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecycler;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar mToolBar;

    private RefreshHandler mRefreshHandler = null;

    /**
     * 初始化布局
     *
     * @return
     */
    private void initRefteshLayout() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    //初始化recyclerview的布局
    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecycler.setLayoutManager(manager);
        //添加分割线
        mRecycler.addItemDecoration(BaseDecoration.creator(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        //获取父级元素
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        //添加布局点击事件
        //如果这个不传ecBottomDelegate父布局 而是传当前delegate的this底部的bottom就会还在
        mRecycler.addOnItemTouchListener(IndexItemClickListener.creator(ecBottomDelegate));
    }

    /**
     * 懒加载不要每次切换界面都刷新
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefteshLayout();
        initRecyclerView();
//        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.creator(mRefreshLayout, mRecycler, new IndexDataConvert());
        CallBackManager.getInstance().addCallBack(CallBackType.ON_SCAN, new IGlobalCallBack<String>() {

            @Override
            public void executeCallBack(@Nullable String args) {
                Toast.makeText(getContext(), "扫描结果:" + args, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //二维码扫描
    @OnClick({R2.id.icon_index_scan})
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }
}
