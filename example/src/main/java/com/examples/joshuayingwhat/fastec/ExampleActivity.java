package com.examples.joshuayingwhat.fastec;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.activities.ProxyActivity;
import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.launcher.LauncherDelegate;
import com.examples.joshuayingwhat.latte.ec.main.EcBottomDelegate;
import com.examples.joshuayingwhat.latte.ec.sign.ISignListener;
import com.examples.joshuayingwhat.latte.ec.sign.SignInDelegate;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackManager;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackType;
import com.examples.joshuayingwhat.latte.utils.callback.IGlobalCallBack;
import com.joshuayingwhat.latte_ui.ui.launcher.ILauncherListener;
import com.joshuayingwhat.latte_ui.ui.launcher.OnLauncherFinishTag;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

/**
 * @author joshuayingwhat
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);

        CallBackManager.getInstance().addCallBack(CallBackType.TAG_OPEN_PUSH, new IGlobalCallBack() {
            @Override
            public void executeCallBack(@Nullable Object args) {
                if (JPushInterface.isPushStopped(Latte.getContext())) {
                    //开启极光推送
                    JPushInterface.setDebugMode(true);
                    JPushInterface.init(ExampleActivity.this);
                }
            }
        }).addCallBack(CallBackType.TAG_CLOSE_PUSH, new IGlobalCallBack() {
            @Override
            public void executeCallBack(@Nullable Object args) {
                    if(!JPushInterface.isPushStopped(Latte.getContext())) {
                        JPushInterface.stopPush(Latte.getContext());
                    }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    /**
     * 登录成功
     */
    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "用户登录成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册成功
     */
    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SINGED://已经登录了
                Toast.makeText(this, "启动结束,用户已经登录了", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SINGED://没有登录
                Toast.makeText(this, "启动结束,用户没登录", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
