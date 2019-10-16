package com.examples.joshuayingwhat.latte.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.examples.joshuayingwhat.latte.delegates.web.chromclient.WebChormClientImpl;
import com.examples.joshuayingwhat.latte.delegates.web.client.WebViewClientImpl;
import com.examples.joshuayingwhat.latte.delegates.web.route.RouteKeys;
import com.examples.joshuayingwhat.latte.delegates.web.route.Router;

public class WebViewDelegateImpl extends WebDelegate {

    public static WebViewDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebViewDelegateImpl delegate = new WebViewDelegateImpl();
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
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
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
        return client;
    }

    @Override
    public WebChromeClient initWebChromClient() {
        return new WebChormClientImpl();
    }
}
