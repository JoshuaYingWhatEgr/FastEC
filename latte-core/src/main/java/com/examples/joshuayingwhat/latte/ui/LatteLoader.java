package com.examples.joshuayingwhat.latte.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.examples.joshuayingwhat.latte.R;
import com.examples.joshuayingwhat.latte.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {

    /**
     * 设置dialog的宽高缩放比  8培
     */
    private static final int LOADER_SIZE_SCALE = 8;

    private static final int LOADER_OFFSET_SCALE = 10;

    /**
     * 创建一个集合把我们的loading加进来 统一管理
     */
    private static ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static String DEFALUT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    private static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.createorLoading(type, context);

        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();

        int deviceHeight = DimenUtil.getScreenHeight();

        Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_OFFSET_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_SIZE_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFALUT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
