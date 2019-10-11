package com.examples.joshuayingwhat.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
