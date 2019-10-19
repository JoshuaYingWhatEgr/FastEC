package com.examples.joshuayingwhat.latte.wechat.templte;

import com.examples.joshuayingwhat.latte.activities.ProxyActivity;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.wechat.BaseWXPayEntryActivity;

/**
 * @author joshuayingwhat
 */
public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        finish();
        //取消动画
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFaile() {
        finish();
        //取消动画
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancle() {
        finish();
        //取消动画
        overridePendingTransition(0, 0);
    }
}
