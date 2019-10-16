package com.examples.joshuayingwhat.latte.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.delegates.web.WebDelegate;
import com.examples.joshuayingwhat.latte.delegates.web.WebViewDelegateImpl;

public class Router {


    private Router() {
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }


    public final boolean handlerWebViewUrl(WebDelegate delegate, String url) {

        //如果是电话
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate parentDelegate = delegate.getParentDelegate();
        final WebViewDelegateImpl webViewDelegate = WebViewDelegateImpl.create(url);
        if (parentDelegate == null) {
            delegate.start(webViewDelegate);
        } else {
            parentDelegate.start(webViewDelegate);
        }
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asste/" + url);
    }

    public void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        context.startActivity(intent);
    }
}
