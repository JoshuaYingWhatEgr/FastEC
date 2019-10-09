package com.examples.joshuayingwhat.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.ec.database.DataBaseManager;
import com.examples.joshuayingwhat.latte.ec.database.UserProfile;

public class SignHandler {

    public static void onSignUp(String response) {
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
