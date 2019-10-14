package com.examples.joshuayingwhat.latte.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @author joshuayingwhat
 */
public class DividerLookUpImpl implements DividerItemDecoration.DividerLookup {

    private final int color;

    private final int size;

    public DividerLookUpImpl(int color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder().size(size).color(color).build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder().size(size).color(color).build();
    }
}
