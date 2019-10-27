package com.examples.joshuayingwhat.latte.ec.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.examples.joshuayingwhat.latte.global.ConfigKeys;
import com.examples.joshuayingwhat.latte.global.Latte;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.examples.joshuayingwhat.latte.net.rx.RxRestClient;
import com.examples.joshuayingwhat.latte.ui.LatteLoader;
import com.examples.joshuayingwhat.latte.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FastPay implements View.OnClickListener {

    //设置支付回调监听
    private IAlPayResutlListener mIAlPayResultListener = null;

    private Activity mActivity = null;

    private AlertDialog mDialog = null;

    private int mOrderId = -1;

    public FastPay(LatteDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay creater(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属行
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            //添加点击事件
            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    /**
     * 支付宝支付
     *
     * @param order
     */
    private void alPay(int order) {
        //签名串 获取签名信息
        final String singUrl = "自己的服务器"+getmOrderId();
        //获取了签名支付串后解析
        RestClient.builder().url(singUrl)
                .success(new ISuccess() {

                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        //异步发起支付请求
                        final PayAsyncTask asyncTask = new PayAsyncTask(mActivity, mIAlPayResultListener);
                        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                }).build().post();
    }

    /**
     * 微信支付
     */
    public final void wxPay(int orderId) {
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "你的服务端微信预支付地址" + getmOrderId();
        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = (String) Latte.getConfigurations().get(ConfigKeys.WE_CHAT_APP_ID.name());
        iwxapi.registerApp(appId);
        RxRestClient.builder()
                .url(weChatPrePayUrl)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        final JSONObject result =
                                JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                });
    }

    @SuppressLint("InvalidR2Usage")
    @Override
    public void onClick(View v) {
        //因为是library所以只能if判断
        int id = v.getId();
        if (id == R2.id.btn_dialog_pay_alpay) {
            //支付宝
            alPay(getmOrderId());
        } else if (id == R2.id.btn_dialog_pay_wechat) {
            //微信
        } else if (id == R2.id.btn_dialog_pay_cancel) {
        }
        mDialog.cancel();
    }

    public FastPay setPayResultListener(IAlPayResutlListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }


    public FastPay setOrderId(int mOrderId) {
        this.mOrderId = mOrderId;
        return this;
    }

    public int getmOrderId() {
        if (mOrderId == -1) {
            try {
                throw new Exception("请初始化ordedID");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mOrderId;
    }

}
