package com.examples.joshuayingwhat.latte.delegates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author joshuayingwhat
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }

        if(rootView != null) {

        }
        return rootView;
    }
}
