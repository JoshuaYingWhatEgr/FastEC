package com.examples.joshuayingwhat.latte.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.blankj.utilcode.util.StringUtils;
import com.examples.joshuayingwhat.latte.net.rx.RxRestClient;
import com.examples.joshuayingwhat.latte.ui.LatteLoader;
import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索功能界面
 *
 * @author joshuayingwhat
 */
public class SearchDelegate extends LatteDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView;

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit;

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        List<MultipleItemEntity> data = new SearchDataConvert().convert();
        final SearchAdapter searchAdapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(searchAdapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();

        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY).build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
        //请求搜索历史记录

    }

    @OnClick(R2.id.tv_top_search)
    void onClickSearch() {

        RxRestClient.builder()
                .url("search.php?key=")
                .loader(getContext())
                .build()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        LatteLoader.stopLoading();
                        final String searchItemText = mSearchEdit.getText().toString();
                        saveItem(searchItemText);
                        mSearchEdit.setText("");
                    }
                });
    }

    /**
     * 将输入的搜索记录存储起来
     *
     * @param item
     */
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            final List<String> history;
            final String historyStr =
                    LattePreference.getCustomAppProfile(SearchEnum.SEATCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);

            LattePreference.addCustomAppProfile(SearchEnum.SEATCH_HISTORY, json);
        }
    }

    @OnClick({R2.id.icon_top_search_back})
    void onClickSearchBack() {
        getSupportDelegate().pop();
    }
}
