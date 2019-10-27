package com.examples.joshuayingwhat.latte.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.examples.joshuayingwhat.latte.global.Latte;

/**
 * @author joshuayingwhat
 */
public class DimenUtil {

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
