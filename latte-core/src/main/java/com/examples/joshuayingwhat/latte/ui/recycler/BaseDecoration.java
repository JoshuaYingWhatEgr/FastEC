package com.examples.joshuayingwhat.latte.ui.recycler;


import androidx.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * recyclerview 分割线
 *
 * @author joshuayingwhat
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookUpImpl(color, size));
    }


    public static BaseDecoration creator(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }

}
