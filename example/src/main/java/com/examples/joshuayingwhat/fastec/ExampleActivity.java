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
        StatusBarCompat.translucentStatusBar(this,true);
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
