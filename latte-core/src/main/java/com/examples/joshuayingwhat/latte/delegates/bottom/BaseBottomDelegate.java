package com.examples.joshuayingwhat.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.examples.joshuayingwhat.latte.R;
import com.examples.joshuayingwhat.latte.R2;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author joshuayingwhat
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final ArrayList<BottomTableBean> TABLE_BEANS = new ArrayList<>();

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<BottomTableBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    @BindView(R2.id.bottom_bar_delegate_container)
    FrameLayout bottomBarDelegateContainer;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat bottomBar;

    private int mCurrentDelegate = 0;

    /**
     * 控制第一个展示的是那个fragment
     */
    private int mIndexDelegate = 0;

    /**
     * 点击bottom之后的颜色
     */
    private int mClickedColor = Color.RED;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract LinkedHashMap<BottomTableBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    /**
     * 设置字体颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTableBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTableBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTableBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TABLE_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, bottomBar);
            //从bottombar中去除bar
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            item.setTag(i);
            //设置每个item的点击事件
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTableBean bean = TABLE_BEANS.get(i);
            //初始化我们的数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        //将每个模块的delegate放载到framelayout
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    private void resetColor() {
        final int count = bottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
