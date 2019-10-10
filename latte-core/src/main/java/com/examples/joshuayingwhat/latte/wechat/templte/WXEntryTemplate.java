package com.examples.joshuayingwhat.latte.wechat.templte;

import com.examples.joshuayingwhat.latte.activities.ProxyActivity;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.wechat.BaseWXActivity;
import com.examples.joshuayingwhat.latte.wechat.BaseWXEntryActivity;
import com.examples.joshuayingwhat.latte.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getmSignInCallBack().onSignInSuccess(userInfo);
    }

}
