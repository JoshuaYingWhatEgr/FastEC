package com.examples.joshuayingwhat.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.examples.joshuayingwhat.latte.global.AccountManager;
import com.examples.joshuayingwhat.latte.global.IUserCheck;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;
import com.joshuayingwhat.latte_ui.ui.launcher.ILauncherListener;
import com.joshuayingwhat.latte_ui.ui.launcher.LauncherHolderCreator;
import com.joshuayingwhat.latte_ui.ui.launcher.OnLauncherFinishTag;
import com.joshuayingwhat.latte_ui.ui.launcher.ScrollLauncherTag;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;

    private ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener iLauncherListener;

    private void initBanner() {

        INTEGERS.add(R.mipmap.launcher_00);
        INTEGERS.add(R.mipmap.launcher_01);

        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            iLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            //标记为true 下次不在出现
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录了
            AccountManager.checkAccount(new IUserCheck() {
                @Override
                public void onSignIn() {
                    iLauncherListener.onLauncherFinish(OnLauncherFinishTag.SINGED);
                }

                @Override
                public void onSignNotIn() {
                    iLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SINGED);
                }
            });
        }
    }
}
