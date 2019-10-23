package com.examples.joshuayingwhat.latte.ec.main.personal.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListAdapter;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListBean;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListItemType;
import com.examples.joshuayingwhat.latte.ec.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人信息界面
 *
 * @author joshuayingwhat
 */
public class UserProfileDelegate extends LatteDelegate {
    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ListBean image = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_AVATAR)
                .setmId(1)
                .setmText("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        ListBean name = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(2)
                .setmText("姓名")
                .setmDelegate(new NameDelegate())
                .setmValue("未设置姓名")
                .build();

        ListBean grander = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(3)
                .setmText("性别")
                .setmValue("未设置性别")
                .build();


        ListBean brith = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(4)
                .setmText("生日")
                .setmValue("未设置生日")
                .build();


        final List<ListBean> data = new ArrayList<>();

        data.add(image);
        data.add(name);
        data.add(grander);
        data.add(brith);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UseProfileClickListener(this));
    }
}
