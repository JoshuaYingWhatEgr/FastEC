package com.examples.joshuayingwhat.latte.wechat;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;

public abstract class BaseWXPayEntryActivity extends BaseWXActivity {

    /**
     * 微信支付成功
     */
    private static final int WX_PAY_SUCCESS = 0;

    /**
     * 微信支付失败
     */
    private static final int WX_PAY_FAILE = -1;

    /**
     * 支付取消
     *
     * @param baseReq
     */
    private static final int WX_PAY_CANCLE = -2;


    protected abstract void onPaySuccess();

    protected abstract void onPayFaile();

    protected abstract void onPayCancle();

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case WX_PAY_SUCCESS:
                    onPaySuccess();
                    break;
                case WX_PAY_FAILE:
                    onPayFaile();
                    break;
                case WX_PAY_CANCLE:
                    onPayCancle();
                    break;
                default:
                    break;
            }
        }
    }
}
