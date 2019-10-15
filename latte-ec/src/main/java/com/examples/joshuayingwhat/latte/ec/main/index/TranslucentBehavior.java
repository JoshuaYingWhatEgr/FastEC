package com.examples.joshuayingwhat.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ui.recycler.RgbValue;

/**
 * @author joshuayingwhat
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;
    //定义变化的颜色
    private final RgbValue RGB_VALUE = RgbValue.creator(255, 124, 2);

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 依赖的view
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //增加滑动距离
        mDistanceY += dy;
        //ToolBar高度
        int targetHeight = child.getBottom();
        //当滑动时，并且距离小于ToolBar高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
