package com.joshuayingwhat.latte_ui.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.widget.IconTextView;
import com.joshuayingwhat.latte_ui.R;

import java.util.ArrayList;

/**
 * @author joshuayingwhat
 */
public class StartLayout extends LinearLayoutCompat implements View.OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-start-0}";
    private static final CharSequence ICON_SELECTED = "{fa-start}";

    private static final int START_TOTAL_COUNT = 5;

    private static final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StartLayout(Context context) {
        this(context, null);
    }

    public StartLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initIcon();
    }

    /**
     * 初始化图标
     */
    private void initIcon() {
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView start = new IconTextView(getContext());
            start.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            start.setLayoutParams(lp);
            start.setText(ICON_UN_SELECT);
            start.setTag(R.id.star_count, i);
            start.setTag(R.id.star_is_select, false);
            start.setOnClickListener(this);
            STARS.add(start);
            this.addView(start);
        }
    }

    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
            if (isSelect) {
                count++;
            }
        }
        return count;
    }

    private void selectStart(int count) {
        for (int i = 0; i <= count; i++) {
            if (i <= count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_SELECTED);
                star.setTextColor(Color.RED);
                star.setTag(R.id.star_is_select, true);
            }
        }
    }

    private void unSelectStar(int count) {
        for (int i = 0; i < START_TOTAL_COUNT; i++) {
            if (i >= count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_select, false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final IconTextView start = (IconTextView) v;
        //获取第几个星
        final int count = (int) start.getTag(R.id.star_count);
        final boolean isSelect = (boolean) start.getTag(R.id.star_is_select);
        if (!isSelect) {
            selectStart(count);
        } else {
            unSelectStar(count);
        }

    }
}
