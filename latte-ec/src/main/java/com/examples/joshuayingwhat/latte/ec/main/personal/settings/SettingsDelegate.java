package com.examples.joshuayingwhat.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.personal.PersonalClickListener;
import com.examples.joshuayingwhat.latte.ec.main.personal.address.AddressDelegate;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListAdapter;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListBean;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListItemType;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackManager;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 系统设置界面
 *
 * @author joshuayingwhat
 */
public class SettingsDelegate extends LatteDelegate {
    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ListBean push = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_SWITCH)
                .setmId(1)
                .setmDelegate(new AddressDelegate())
                .setmOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            //打开
                            CallBackManager.getInstance().getCallBack(CallBackType.TAG_OPEN_PUSH).executeCallBack(null);
                        }else {
                            //关闭
                            CallBackManager.getInstance().getCallBack(CallBackType.TAG_CLOSE_PUSH).executeCallBack(null);
                        }
                    }
                })
                .setmText("消息推送")
                .build();

        ListBean about = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(2)
                .setmDelegate(new AboutDelegate())
                .setmText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();

        data.add(push);
        data.add(about);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
