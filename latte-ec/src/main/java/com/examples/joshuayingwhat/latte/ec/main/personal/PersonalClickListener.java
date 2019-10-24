package com.examples.joshuayingwhat.latte.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListBean;

import java.util.List;

public class PersonalClickListener extends SimpleClickListener {

    private final LatteDelegate delegate;

    public PersonalClickListener(LatteDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getmId();
        switch (id) {
            case 1:
                delegate.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
