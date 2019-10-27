package com.joshuayingwhat.latte_ui.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarqueeTextView(Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //设置单行
        setSingleLine();
        //设置Ellipsize
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //获取焦点
        setFocusable(true);
        //走马灯的重复次数，-1代表无限重复
        setMarqueeRepeatLimit(-1);
        //强制获得焦点
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
