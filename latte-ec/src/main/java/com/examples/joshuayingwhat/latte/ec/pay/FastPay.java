package com.examples.joshuayingwhat.latte.ec.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;

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

    @SuppressLint("InvalidR2Usage")
    @Override
    public void onClick(View v) {
        //因为是library所以只能if判断
        int id = v.getId();
        if (id == R2.id.btn_dialog_pay_alpay) {
            //支付宝
        } else if (id == R2.id.btn_dialog_pay_wechat) {
            //微信
        } else if (id == R2.id.btn_dialog_pay_cancel) {
        }
        mDialog.cancel();
    }
}
