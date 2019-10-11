package com.examples.joshuayingwhat.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;

/**
 * @author joshuayingwhat
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
