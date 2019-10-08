package com.examples.joshuayingwhat.latte.ec.launcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ui.launcher.ScrollLauncherTag;
import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;
import com.examples.joshuayingwhat.latte.utils.timer.BaseTimerTask;
import com.examples.joshuayingwhat.latte.utils.timer.ITimerListener;


import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * app启动引导图
 *
 * @author joshuayingwhat
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;

    private Timer mTimer = null;

    private int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowingScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了app

        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvLauncherTimer != null) {
                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowingScroll();
                        }
                    }
                }
            }
        });
    }

    /**
     * 初始化timer
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @SuppressLint("InvalidR2Usage")
    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.tv_launcher_timer:
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    checkIsShowingScroll();
                }
                break;
            default:
                break;
        }
    }
}
