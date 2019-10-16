package com.examples.joshuayingwhat.latte.delegates.web;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

public class WebViewInitialize implements View.OnLongClickListener {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public WebView createWebView(WebView webView) {


        /**
         * 不能横向滚动
         */
        webView.setHorizontalScrollBarEnabled(false);

        /**
         * 不能纵向滚动
         */
        webView.setVerticalScrollBarEnabled(false);

        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(this);
        //初始化websettings
        final WebSettings settings = webView.getSettings();
        String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "Latte");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //屏蔽缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }
}
