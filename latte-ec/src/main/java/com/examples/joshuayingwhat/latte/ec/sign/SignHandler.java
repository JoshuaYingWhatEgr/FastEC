package com.examples.joshuayingwhat.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.global.AccountManager;
import com.examples.joshuayingwhat.latte.ec.database.DataBaseManager;
import com.examples.joshuayingwhat.latte.ec.database.UserProfile;

/**
 * @author joshuayingwhat
 */
public class SignHandler {

    public static void onSignUp(String response, ISignListener mISignListener) {
        dealSignResponse(response);

        /**
         *用户账号创建成功了，我们就保存账号登录的状态
         */
        AccountManager.setSignState(true);
        /**
         * 注册成功
         */
        mISignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener mISignListener) {
        dealSignResponse(response);

        /**
         *用户账号创建成功了，我们就保存账号登录的状态
         */
        AccountManager.setSignState(true);
        /**
         * 登录成功
         */
        mISignListener.onSignInSuccess();
    }

    private static void dealSignResponse(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DataBaseManager.getInstance().getmDao().insert(profile);
    }
}
