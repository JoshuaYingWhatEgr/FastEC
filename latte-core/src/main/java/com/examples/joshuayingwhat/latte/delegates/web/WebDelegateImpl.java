package com.examples.joshuayingwhat.latte.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.examples.joshuayingwhat.latte.delegates.IPageLoadListener;
import com.examples.joshuayingwhat.latte.delegates.web.chromclient.WebChormClientImpl;
import com.examples.joshuayingwhat.latte.delegates.web.client.WebViewClientImpl;
import com.examples.joshuayingwhat.latte.delegates.web.route.RouteKeys;
import com.examples.joshuayingwhat.latte.delegates.web.route.Router;

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            Router.getInstance().loadPage(this, getUrl());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitialize().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromClient() {
        return new WebChormClientImpl();
    }
}
