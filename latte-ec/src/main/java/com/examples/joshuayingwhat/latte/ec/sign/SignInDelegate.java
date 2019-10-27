package com.examples.joshuayingwhat.latte.ec.sign;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.EcBottomDelegate;
import com.examples.joshuayingwhat.latte.wechat.LatteWeChat;
import com.examples.joshuayingwhat.latte.wechat.callback.IWeChatSignInCallBack;
import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 *
 * @author joshuayingwhat
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;

    private ISignListener iSignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            iSignListener = (ISignListener) activity;
        }
    }

    public boolean checkForm() {

        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("邮箱格式输入错误");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请填写至少6位密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.btn_sign_in, R2.id.tv_link_sign_up, R2.id.icon_sign_in_wechat})
    public void onViewClicked(View view) {
        int id = view.getId();//登录
        if (id == R.id.btn_sign_in) {
            if (checkForm()) {
                getSupportDelegate().startWithPop(new EcBottomDelegate());
            }

        } else if (id == R.id.tv_link_sign_up) {//还没有注册 去注册

            getSupportDelegate().startWithPop(new SignUpDelegate());

        } else if (id == R.id.icon_sign_in_wechat) {//微信登录
            LatteWeChat.getInstance().onSignInSuccess(new IWeChatSignInCallBack() {
                @Override
                public void onSignInSuccess(String userInfo) {
                    
                }
            }).signIn();
        }
    }
}
