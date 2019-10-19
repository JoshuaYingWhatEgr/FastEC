package com.examples.joshuayingwhat.latte.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.examples.joshuayingwhat.latte.ui.LatteLoader;

public class PayAsyncTask extends AsyncTask<String, Void, String> {

    private final Activity ACTIVITY;

    private final IAlPayResutlListener LISTENER;

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";

    //订单处理中
    private static final String AL_PAY_STATUS_PAGING = "8000";

    //订单支付失败
    private static final String AL_PAY_STATUS_FAILE = "4000";

    //用户zhongtu 取消
    private static final String AL_PAY_STATU_CANCLE = "6001";

    //网络错误
    private static final String AL_PAY_STATU_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResutlListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        //取出签名信息
        final String alPaySign = strings[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(alPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        LatteLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();

        if (LISTENER == null) {
            throw new NullPointerException("IAlPayResutlListener is NULL");
        }

        switch (resultStatus) {

            case AL_PAY_STATUS_SUCCESS:
                LISTENER.onPaySuccess();
                break;
            case AL_PAY_STATUS_PAGING:
                LISTENER.onPaying();
                break;
            case AL_PAY_STATUS_FAILE:
                LISTENER.onPayFail();
                break;
            case AL_PAY_STATU_CANCLE:
                LISTENER.onPayCancle();
                break;
            case AL_PAY_STATU_CONNECT_ERROR:
                LISTENER.onPayConnectError();
                break;
            default:
                break;
        }
    }
}

