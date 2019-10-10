package com.examples.joshuayingwhat.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 微信登录成功后的回调
     */
    public abstract void onSignInSuccess(String userInfo);


    //第三方应用发送请求给微信的回调
    @Override
    public void onResp(BaseResp baseResp) {

        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();

        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        getAuth(authUrl.toString());
    }

    //微信发送请求到第三方应用
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //通过code获取access_token
    private void getAuth(String authUrl) {
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSON.parseObject(response);
                        //接口调用凭证
                        final String accessToken = authObj.getString("access_token");
                        //授权用户唯一标识
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient.builder().url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response.toString());
                    }
                }).build().get();
    }
}
