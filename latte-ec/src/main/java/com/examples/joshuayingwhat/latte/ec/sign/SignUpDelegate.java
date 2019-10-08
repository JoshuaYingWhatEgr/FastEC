package com.examples.joshuayingwhat.latte.ec.sign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    private boolean checkForm() {
        String name = editSignUpName.getText().toString();
        String email = editSignUpEmail.getText().toString();
        String phone = editSignUpPhone.getText().toString();
        String password = editSignUpPassword.getText().toString();
        String rePassword = editSignUpRePassword.getText().toString();
        boolean isPass = true;
        if (name.isEmpty()) {
            editSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            editSignUpPhone.setError("手机号码错误");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("请输入大于6位的密码");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.length() < 6 || rePassword.isEmpty() || !(rePassword.equals(password))) {
            editSignUpRePassword.setError("请输入大于6位的密码或者密码输入错误");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }
        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.btn_sign_up, R2.id.tv_link_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.btn_sign_up:
                //用户所有的信息都填写成功
                if (checkForm()) {
                    RestClient.builder().url("sign_up")
                            .params("", "")
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {

                                }
                            }).error(new IError() {
                        @Override
                        public void onError(int code, String msg) {

                        }
                    }).builder().post();
                }

                break;
            case R2.id.tv_link_sign_in:
                break;
        }
    }
}
