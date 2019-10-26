package com.examples.joshuayingwhat.latte.ui.scanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.examples.joshuayingwhat.latte.R;

import me.dm7.barcodescanner.core.ViewFinderView;

public class LatteViewFinderView extends ViewFinderView {
    public LatteViewFinderView(Context context) {
        this(context, null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }


}
