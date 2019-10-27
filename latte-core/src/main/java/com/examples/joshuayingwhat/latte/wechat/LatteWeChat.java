package com.examples.joshuayingwhat.latte.wechat;

import android.app.Activity;

import com.examples.joshuayingwhat.latte.global.ConfigKeys;
import com.examples.joshuayingwhat.latte.global.Latte;
import com.examples.joshuayingwhat.latte.wechat.callback.IWeChatSignInCallBack;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LatteWeChat {
    static final String APP_ID = (String) Latte.getConfigurations().get(ConfigKeys.WE_CHAT_APP_ID.name());

    static final String APP_SECRET = (String) Latte.getConfigurations().get(ConfigKeys.WE_CHAT_APP_SECRET.name());

    private final IWXAPI WXAPI;

    private IWeChatSignInCallBack mSignInCallBack = null;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private LatteWeChat() {
        final Activity activity = (Activity) Latte.getConfigurations().get(ConfigKeys.ACTIVITY.name());
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWeChat onSignInSuccess(IWeChatSignInCallBack weChatSignInCallBack) {
        this.mSignInCallBack = weChatSignInCallBack;
        return this;
    }

    public IWeChatSignInCallBack getmSignInCallBack() {
        return mSignInCallBack;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
