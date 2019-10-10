package com.examples.joshuayingwhat.latte.wechat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * @author joshuayingwhat
 */
public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LatteWeChat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LatteWeChat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }
}
